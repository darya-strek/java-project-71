run-dist:
	make -C app run-dist

run:
	make -C app run

report:
    	make -C app report

build:
	make -C app build

test:
	make -C app test

lint:
	make -C app lint

.PHONY: build
