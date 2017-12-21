(ns game-of-life-quil.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(enable-console-print!)

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :hsb 360 100 100)
  {:grid-size 72})

(defn update-state [state]
  state)

(defn draw-grid [cell-width cell-height]
  (q/stroke 0)
  (doseq [x (range cell-width (q/width) cell-width)]
    (q/line x 0 x (q/height)))
  (doseq [y (range cell-width (q/height) cell-height)]
    (q/line 0 y (q/width) y)))

(defn draw-state [state]
  (q/background 360 0 100)
  (let [{:keys [grid-size]} state
        cell-width (/ (q/width) grid-size)
        cell-height (/ (q/height) grid-size)]
    (draw-grid cell-width cell-height)))

(q/defsketch game-of-life-quil
  :host "game-of-life-quil"
  :size [720 720]
  :setup setup
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode])
