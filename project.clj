(defproject instafav "0.1.0-SNAPSHOT"
  :description "Simple app to favorite certain friends."
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.json "0.2.6"]
                 [instagram-api "0.1.8"]
                 [environ "1.0.0"]
                 [http-kit "2.1.16"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-defaults "0.1.4"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [compojure "1.3.2"]
                 [hiccup "1.0.5"]
                 [korma "0.4.0"]
                 [org.postgresql/postgresql "9.4-1201-jdbc4"]]
  :plugins [[lein-ring "0.9.3"]
            [lein-cljfmt "0.1.10"]]
  :ring {:handler instafav.web/app}
  :main ^:skip-aot instafav.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
