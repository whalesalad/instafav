(ns instafav.core
  (:gen-class)
  (:require
    instagram.oauth
    instagram.callbacks
    instagram.callbacks.handlers
    instagram.api.endpoint)
  (:import
    (instagram.callbacks.protocols SyncSingleCallback)))

(require '[environ.core :refer [env]])

(def client-id
  (env :instagram-client-id))

(def client-secret
  (env :instagram-client-secret))

(def redirect-uri
  (env :instagram-redirect-uri))

(def ^:dynamic *creds* (instagram.oauth/make-oauth-creds client-id
                                                         client-secret
                                                         redirect-uri))

(def ^:dynamic *auth-url* (instagram.api.endpoint/authorization-url *creds* "basic"))

; (let [access-token (-> (get-access-token *creds* ))])

(defn -main
  [& args]
  (println *creds*)
  (println *auth-url*)
  (println args))
