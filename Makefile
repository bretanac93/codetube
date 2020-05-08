.PHONY: all build test ping-mysql

all: build

up:
	@docker-compose up -d

build:
	@./gradlew build --warning-mode all

test-local:
	@./gradlew test --warning-mode all

test:
	@docker-compose exec codetube-java

run:
	@./gradlew :run

ping-mysql:
	@docker-compose exec codetube-mysql mysqladmin --user=root --password= --host "127.0.0.1" ping --silent

start-backoffice:
	@./gradlew :run --args="backoffice server"
