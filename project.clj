(defproject lein-httpd "1.0.0"
  :description "Start a web server in the current directory."
  :url "https://github.com/malyn/lein-httpd"
  :license {:name "BSD"
            :url "http://www.opensource.org/licenses/BSD-3-Clause"
            :distribution :repo }

  :dependencies [[org.eclipse.jetty/jetty-server "9.0.0.RC2"]]

  :profiles {:dev {:dependencies [[speclj "2.5.0"]]}}
  :plugins [[speclj "2.5.0"]]
  :test-paths ["spec"]

  :eval-in-leiningen true)
