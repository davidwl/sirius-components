/*******************************************************************************
 * Copyright (c) 2022 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
import { SubscriptionResult } from '@apollo/client';
import {
  GQLFormDescriptionEditor,
  GQLFormDescriptionEditorEventPayload,
  GQLFormDescriptionEditorEventSubscription,
  GQLFormDescriptionEditorRefreshedEventPayload,
  GQLSubscribersUpdatedEventPayload,
  Subscriber,
} from 'formdescriptioneditor/FormDescriptionEditorEventFragment.types';
import { v4 as uuid } from 'uuid';
import { assign, Machine } from 'xstate';

export interface FormDescriptionEditorWebSocketContainerStateSchema {
  states: {
    toast: {
      states: {
        visible: {};
        hidden: {};
      };
    };
    formDescriptionEditorWebSocketContainer: {
      states: {
        empty: {};
        loading: {};
        ready: {};
        complete: {};
      };
    };
  };
}

export type SchemaValue = {
  toast: 'visible' | 'hidden';
  formDescriptionEditorWebSocketContainer: 'loading' | 'ready' | 'empty' | 'complete';
};

export interface FormDescriptionEditorWebSocketContainerContext {
  id: string;
  formDescriptionEditor: GQLFormDescriptionEditor;
  subscribers: Subscriber[];
  message: string | null;
}
export type ShowToastEvent = { type: 'SHOW_TOAST'; message: string };
export type HideToastEvent = { type: 'HIDE_TOAST' };
export type HandleSubscriptionResultEvent = {
  type: 'HANDLE_SUBSCRIPTION_RESULT';
  result: SubscriptionResult<GQLFormDescriptionEditorEventSubscription>;
};
export type CompleteEvent = { type: 'HANDLE_COMPLETE' };

export type InitializeRepresentationEvent = {
  type: 'INITIALIZE';
};

export type FormDescriptionEditorWebSocketContainerEvent =
  | ShowToastEvent
  | HideToastEvent
  | CompleteEvent
  | InitializeRepresentationEvent
  | HandleSubscriptionResultEvent;

const isFormDescriptionEditorRefreshedEventPayload = (
  payload: GQLFormDescriptionEditorEventPayload
): payload is GQLFormDescriptionEditorRefreshedEventPayload =>
  payload.__typename === 'FormDescriptionEditorRefreshedEventPayload';
const isSubscribersUpdatedEventPayload = (
  payload: GQLFormDescriptionEditorEventPayload
): payload is GQLSubscribersUpdatedEventPayload => payload.__typename === 'SubscribersUpdatedEventPayload';

export const formDescriptionEditorWebSocketContainerMachine = Machine<
  FormDescriptionEditorWebSocketContainerContext,
  FormDescriptionEditorWebSocketContainerStateSchema,
  FormDescriptionEditorWebSocketContainerEvent
>(
  {
    type: 'parallel',
    context: {
      id: uuid(),
      formDescriptionEditor: null,
      subscribers: [],
      message: null,
    },
    states: {
      toast: {
        initial: 'hidden',
        states: {
          hidden: {
            on: {
              SHOW_TOAST: {
                target: 'visible',
                actions: 'setMessage',
              },
            },
          },
          visible: {
            on: {
              HIDE_TOAST: {
                target: 'hidden',
                actions: 'clearMessage',
              },
            },
          },
        },
      },
      formDescriptionEditorWebSocketContainer: {
        initial: 'loading',
        states: {
          empty: {},
          loading: {
            on: {
              INITIALIZE: [
                {
                  target: 'ready',
                  actions: 'initialize',
                },
              ],
            },
          },
          ready: {
            on: {
              HANDLE_SUBSCRIPTION_RESULT: [
                {
                  actions: 'handleSubscriptionResult',
                },
              ],
              HANDLE_COMPLETE: [
                {
                  target: 'complete',
                  actions: 'handleComplete',
                },
              ],
            },
          },
          complete: {},
        },
      },
    },
  },
  {
    actions: {
      initialize: assign((_) => {
        return {
          message: undefined,
        };
      }),
      handleComplete: assign((_) => {
        return {};
      }),
      handleSubscriptionResult: assign((_, event) => {
        const { result } = event as HandleSubscriptionResultEvent;
        const { data } = result;
        if (isFormDescriptionEditorRefreshedEventPayload(data.formDescriptionEditorEvent)) {
          const { formDescriptionEditor } = data.formDescriptionEditorEvent;
          return { formDescriptionEditor };
        } else if (isSubscribersUpdatedEventPayload(data.formDescriptionEditorEvent)) {
          const { subscribers } = data.formDescriptionEditorEvent;
          return { subscribers };
        }
        return {};
      }),
      setMessage: assign((_, event) => {
        const { message } = event as ShowToastEvent;
        return { message };
      }),
      clearMessage: assign((_) => {
        return { message: null };
      }),
    },
  }
);
