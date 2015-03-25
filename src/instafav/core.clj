(ns instafav.core
  (:gen-class)
  (:require
    [ring.adapter.jetty :as ring]
    [instafav.web :as web]))

(defn -main [& args]
  (ring/run-jetty web/routes { :port 8080 }))
