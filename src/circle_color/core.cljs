(ns circle-color.core
  (:require [clojure.browser.repl :as repl]
            [React :refer [createElement createClass render]]
            [Circle :as Circle]))

(def ColorInput
  (createClass
   #js {:render
        (fn []
          (this-as this
            (createElement "div" nil
              (createElement "input" #js {:type "text"
                                          :className "center"
                                          :onChange (.. this -props -onChange)}))))}))

(def Container
  (createClass
   #js {:getInitialState (fn [] #js {:color ""})
        :handleColorChange (fn [event]
                             (this-as this
                               (.setState this #js {:color (.. event -target -value)})))
        :render (fn []
                  (this-as this
                    (createElement "div" nil
                      (createElement ColorInput #js {:onChange (. this -handleColorChange)})
                      (createElement js/Circle #js {:color (.. this -state -color)}))))}))

(render
 (createElement Container)
 (.getElementById js/document "app"))



