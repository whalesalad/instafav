(ns instafav.core
  (:gen-class)
  (:require
    [org.httpkit.server :refer [run-server]]
    [ring.adapter.jetty :as ring]
    [instafav.web :as web]))

; (defn app-server
;   ring/run-jetty)

(defn -main [& args]
  (ring/run-jetty web/app {:port 8080}))
