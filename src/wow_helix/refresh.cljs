(ns wow-helix.refresh
  (:require [helix.experimental.refresh :as her]))

(her/inject-hook!)

(defn ^:dev/after-load refresh []
  (her/refresh!))
