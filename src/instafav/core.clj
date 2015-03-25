(ns instafav.core
  (:gen-class)
  (:require
    instagram.oauth
    instagram.callbacks
    instagram.callbacks.handlers
    instagram.api.endpoint
    [ring.adapter.jetty :as jetty]
    [clojure.data.json :as json])
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

(defn handler [request]
  { :status 200
    :headers {"Content-Type" "application/json" }
    :body (json/write-str *creds*) })

(defn -main [& args]
  (jetty/run-jetty handler { :port 8080 }))
