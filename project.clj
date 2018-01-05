(defproject game-of-life-quil "0.1.0-SNAPSHOT"
  :description "Conway's Game of Life"
  :url "https://github.com/jdormit/game-of-life-quil"
  :license {:name "MIT"
            :url "https://raw.githubusercontent.com/jdormit/game-of-life-quil/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.match "0.3.0-alpha5"]
                 [quil "2.6.0"]
                 [org.clojure/clojurescript "1.9.473"]]

  :plugins [[lein-cljsbuild "1.1.5"]]
  :hooks [leiningen.cljsbuild]
  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.2"]
                                  [org.clojure/tools.nrepl "0.2.10"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}}

  :cljsbuild
  {:builds [{:source-paths ["src"]
             :compiler
             {:output-to "js/main.js"
              :output-dir "out"
              :main "game_of_life_quil.core"
              :optimizations :none
              :pretty-print true}}]})
