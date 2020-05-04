
repl:
	clj -m cljs.main -co build.edn -r

advanced:
	clj -m cljs.main -co build.edn  -O advanced -c

serve:
	clj -m cljs.main --serve

