(ns wow-calc-helix.render-util
  #?(:clj  (:require [helix.core])
     :cljs (:require-macros [wow-calc-helix.render-util])))

#?(:clj
   (defmacro defnc [type & form-body]
     (let [[docstring form-body] (if (string? (first form-body))
                                   [(first form-body) (rest form-body)]
                                   [nil form-body])
           [fn-meta form-body] (if (map? (first form-body))
                                 [(first form-body) (rest form-body)]
                                 [nil form-body])
           params (first form-body)
           body (rest form-body)
           opts-map? (map? (first body))
           opts (cond-> (if opts-map?
                          (first body)
                          {})
                  (:wrap fn-meta) (assoc :wrap (:wrap fn-meta)))
           ;; feature flags to enable by default
           default-opts {:helix/features {:check-invalid-hooks-usage true
                                          :fast-refresh true}}]
       `(helix.core/defnc ~type
          ~@(when docstring [docstring])
          ~@(when fn-meta [fn-meta])
          ~params
          ;; we use `merge` here to allow indidivual consumers to override feature
          ;; flags in special cases
          ~(merge default-opts opts)
          ~@body))))
