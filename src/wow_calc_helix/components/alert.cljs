(ns wow-calc-helix.components.alert
  (:require
   [wow-calc-helix.render-util :refer [defnc]]
   [helix.core :refer [$]]
   ["@mui/material/Alert" :default Alert]))

(declare alert children best?)

(defnc alert [{:keys [children best?]}]
  (let [variant (if best? "filled" "outlined")
        severity (if best? "success" "info")]
    ($ Alert {:variant variant
              :severity severity} children)))
