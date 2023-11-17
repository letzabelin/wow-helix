(ns wow-helix.pages.home
  (:require
   [wow-helix.render-util :refer [defnc]]
   [helix.dom :as d]
   [helix.core :refer [<> $]]
   [helix.hooks :refer [use-state]]
   [cljs.pprint :as pprint]
   [wow-helix.components.numberfield :refer [numberfield]]
   [wow-helix.components.button :refer [button]]
   [wow-helix.components.alert :refer [alert]]
   [wow-helix.util :refer [calc-large calc-small]]))

(declare home-page)

(defnc home-page []
  (let [[small set-small] (use-state 0)
        [large set-large] (use-state 0)
        [small-result set-small-result] (use-state 0)
        [large-result set-large-result] (use-state 0)]
    (letfn [(handle-small-change [evt] (set-small (.-value (.-target evt))))
            (handle-large-change [evt] (set-large (.-value (.-target evt))))
            (calc-result [] (do (set-large-result (calc-large large small))
                                (set-small-result (calc-small large small))))
            (reset-all [] (do (set-small 0)
                              (set-large 0)
                              (set-small-result 0)
                              (set-large-result 0)))]
      (d/main {:class ["wrapper"]}
              (<> (d/div {:class ["group"]}
                         (<> (d/div {:class ["group__form"]} (<> ($ numberfield {:value large
                                                                                 :on-change handle-large-change
                                                                                 :label "Большая:"})
                                                                 ($ numberfield {:value small
                                                                                 :on-change handle-small-change
                                                                                 :label "Малая:"})))
                             (d/div {:class ["group__button"]}
                                    ($ button {:on-click calc-result} "Посчитать"))
                             (d/div {:class ["group__result"]}
                                    (<> ($ alert {:best? (and (pos? small-result) (> small-result large-result))} 
                                           (pprint/cl-format nil "Из малых в большие: ~,2f" small-result)))
                                    (<> ($ alert {:best? (and (pos? large-result) (> large-result small-result))} 
                                           (pprint/cl-format nil "Из больших в малые: ~,2f" large-result))))
                             (d/div {:class ["group__button"]}
                                    ($ button {:on-click reset-all} "Сбросить")))))))))
