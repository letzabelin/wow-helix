(ns wow-calc-helix.app
  (:require
   [wow-calc-helix.render-util :refer [defnc]]
   [helix.core :refer [$]]
   [wow-calc-helix.pages.home :refer [home-page]]
   ["react-dom/client" :refer [createRoot] :rename {createRoot create-root}]))

(declare app)

(defnc app []
  (home-page $))

(defonce root (create-root (js/document.getElementById "root")))

(defn ^:dev/after-load mount-ui []
  (.render root ($ app)))

(defn ^:export init []
  (mount-ui))
