(ns wow-calc-helix.components.numberfield
  (:require
   [wow-calc-helix.render-util :refer [defnc]]
   [helix.core :refer [$]]
   ["@mui/material/TextField" :default TextField]
   ["@mui/material/InputAdornment" :default InputAdornment]))

(declare numberfield label value on-change)

(defnc numberfield [{:keys [label value on-change]}]
  ($ TextField {:type "number"
                :value value
                :onChange on-change
                :style (js-obj "width" "500px")
                :InputProps (js-obj "inputProps" (js-obj "min" 0)
                                    "startAdornment" ($ InputAdornment {:position "start"} label))}))
