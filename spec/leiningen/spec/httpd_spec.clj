(ns leiningen.spec.httpd-spec
  (:use [speclj.core]
        [leiningen.httpd]))

(describe "lein httpd"
  (with port (atom nil))

  (around [it]
    (let [run-server (ns-resolve 'leiningen.httpd (symbol "run-server"))
          get-port (fn [server] (-> server .getConnectors first .getPort))]
      (with-redefs-fn {run-server #(reset! @port (get-port %))}
        it)))

  (describe "outside of a project"
    (it "uses a default port of 8080"
      (httpd nil)
      (should= 8080 @@port))
    (it "allows the port to be overridden"
      (httpd nil "8027")
      (should= 8027 @@port)))

  (describe "in a project"
    (it "uses a default port of 8080"
      (httpd {})
      (should= 8080 @@port))
    (it "allows the port to be overridden"
      (httpd {} "8027")
      (should= 8027 @@port))))
