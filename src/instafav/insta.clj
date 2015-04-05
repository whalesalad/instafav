(ns instafav.insta
  (:require
    instagram.oauth
    instagram.callbacks
    instagram.callbacks.handlers
    instagram.api.endpoint
    [environ.core :refer [env]])
  (:import
    (instagram.callbacks.protocols SyncSingleCallback)))

(def client-id
  (env :instagram-client-id))

(def client-secret
  (env :instagram-client-secret))

(def redirect-uri
  (env :instagram-redirect-uri))

(def credentials (instagram.oauth/make-oauth-creds client-id
                                                   client-secret
                                                   redirect-uri))

(def auth-url (instagram.api.endpoint/authorization-url credentials "basic"))

(defn get-access-token [code]
  (instagram.api.endpoint/get-access-token credentials code))

(def access-token
  (env :instagram-token))

(defn follows [token]
  (instagram.api.endpoint/get-followings :access-token token))
