(ns instafav.storage
  (:use korma.db)
  (:require
    [clojure.str :as str]))

(defdb dev (postgres {:db "instafav"
                      :host "localhost"}))
