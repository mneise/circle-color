(require '[cljs.build.api :as b]
         '[clojure.java.io :as io])
(refer 'cljs.closure :only '[js-transforms])
(import 'javax.script.ScriptEngineManager)

(defmethod js-transforms :jsx [ijs opts]
  (let [engine (doto (.getEngineByName (ScriptEngineManager.) "nashorn")
                 (.eval (io/reader (io/file "jstransform-simple.bundle.js")))
                 (.put "originalCode" (:source ijs)))]
    (assoc ijs :source
      (.eval engine (str "simple.transform(originalCode, {react: true}).code")))))

(println "Building ...")

(let [start (System/nanoTime)]
  (b/build "src"
    {:main 'circle-color.core
     :asset-path "js/out"
     :output-to "resources/public/js/out/circle_color.js"
     :output-dir "resources/public/js/out"
     :verbose true
     :pretty-print true
     :foreign-libs [{:file "resources/public/js/libs/react.js"
                     :provides ["React"]
                     :module-type :commonjs}
                    {:file "resources/public/js/libs/Circle.js"
                     :provides ["Circle"]
                     :module-type :commonjs
                     :preprocess :jsx}]
     :optimizations :simple
     :closure-warnings {:non-standard-jsdoc :off}})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))
