(ns game-of-life-quil.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]
            [clojure.core.match :refer [match]]
            [game-of-life-quil.svgs :as svgs]))

(enable-console-print!)

(defn setup []
  (q/frame-rate 5)
  (q/color-mode :hsb 360 100 100)
  {:paused (atom false)
   :button-icon (atom :pause)
   :play-pause-button (.getElementById js/document "play-pause-button")
   :grid-size 72
   :cells (for [col (range 30 40)] (list 36 col))})

(defn get-neighbors [[r c] grid-size]
  (filter #(not (= % [r c]))
          (for [row (range (- r 1) (+ r 2))
                :when (and (>= row 0) (< row grid-size))
                col (range (- c 1) (+ c 2))
                :when (and (>= col 0) (< col grid-size))]
            (list row col))))

(defn cell-is-populated
  [cell cells]
  (not (nil? (some (set (list cell)) cells))))

(defn process-cells [[f-cell & r-cells] grid-size cells-visited active-cells acc]
  (if (nil? f-cell)
    acc
    (let [neighbors (get-neighbors f-cell grid-size)]
      (if (some (set (list f-cell)) cells-visited)
        (recur r-cells grid-size cells-visited active-cells acc)
        (match [(cell-is-populated f-cell active-cells)
                (count
                 (filter true?
                         (map #(cell-is-populated % active-cells) neighbors)))]
               [true (_ :guard #(or (>= % 4) (<= % 1)))] ;; 0, 1, 4 or more neighbors, cell dies
               (recur (concat r-cells neighbors)
                      grid-size
                      (conj cells-visited f-cell)
                      active-cells
                      (remove #{f-cell} acc))
               [true _] ;; 2 or 3 neighbors, cell lives
               (recur (concat r-cells neighbors)
                      grid-size
                      (conj cells-visited f-cell)
                      active-cells
                      acc)
               [false (_ :guard #(= % 3))] ;; 3 neighbors, cell becomes populated
               (recur r-cells
                      grid-size
                      (conj cells-visited f-cell)
                      active-cells
                      (conj acc f-cell))
               [false _] ;; cell remains unpopulated
               (recur r-cells
                      grid-size
                      (conj cells-visited f-cell)
                      active-cells
                      acc)
               [populated neighbors] (println (str "Unhandled case" populated neighbors)))))))

(defn game-of-life [state]
  (let [{:keys [cells grid-size paused]} state]
    (if @paused
      state
      (assoc state :cells (process-cells cells
                                        grid-size
                                        []
                                        cells
                                        cells)))))

;; TODO why is the click event firing multiple times per click?
(defn handle-events [state]
  (let [{:keys [play-pause-button paused button-icon]} state]
    (.addEventListener play-pause-button
                       "click"
                       (fn [e]
                         (.preventDefault e)
                         (.stopPropagation e)
                         (swap! paused #(not %))
                         (swap! button-icon #(if @paused :pause :play))))
    state))

(defn update-state [state]
  (-> state
      (handle-events)
      (game-of-life)))

(defn draw-grid [cell-width cell-height]
  (q/stroke 0)
  (doseq [x (range cell-width (q/width) cell-width)]
    (q/line x 0 x (q/height)))
  (doseq [y (range cell-width (q/height) cell-height)]
    (q/line 0 y (q/width) y)))

(defn draw-cells [cells cell-width cell-height grid-size]
  (doseq [cell cells]
    (let [[row col] cell]
      (q/fill 0)
      (q/rect (* col cell-width) (* row cell-height) cell-width cell-height))))

(defn render-button [button paused icon]
  (cond
    (and @paused (= @icon :pause)) (set! (.-innerHTML button) svgs/play)
    (and (not @paused) (= @icon :play)) (set! (.-innerHTML button) svgs/pause)))

(defn draw-state [state]
  (q/background 360 0 100)
  (let [{:keys [grid-size cells play-pause-button paused button-icon]} state
        cell-width (/ (q/width) grid-size)
        cell-height (/ (q/height) grid-size)]
    (draw-grid cell-width cell-height)
    (draw-cells cells cell-width cell-height grid-size)
    (render-button play-pause-button paused button-icon)))

(q/defsketch game-of-life-quil
  :host "game-of-life-quil"
  :size [720 720]
  :setup setup
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode])
