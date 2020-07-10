(ns hello-world.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [re-frame.core :as rf]))

(rf/reg-sub
  ::throwing-sub
  (fn [_ _]
    (assert (odd? 2))
    "fn sub"))

(defn app []
  [:p @(rf/subscribe [::throwing-sub])])

(defn error-boundary []
  (let [*error (r/atom nil)]
    (r/create-class
      {:get-derived-state-from-error (fn [e] (reset! *error e))
       :component-did-catch          (fn [_ _ _] true)
       :reagent-render               (fn [form] (if @*error [:div (str @*error)] form))})))

(rdom/render [error-boundary [app]] (js/document.getElementById "app"))
