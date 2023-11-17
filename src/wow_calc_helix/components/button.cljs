(ns wow-calc-helix.components.button
  (:require
   [wow-calc-helix.render-util :refer [defnc]]
   [helix.core :refer [$]]
   ["@mui/material/Button" :default Button]))

(declare button children on-click)

(defnc button [{:keys [children on-click]}]
  ($ Button {:variant "outlined"
             :style (js-obj "height" "100%")
             :onClick on-click} children))
