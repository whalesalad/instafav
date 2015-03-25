(defproject instafav "0.1.0-SNAPSHOT"
  :description "Simple app to favorite certain friends."
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [instagram-api "0.1.8"]
                 [environ "1.0.0"]]
  :main ^:skip-aot instafav.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
