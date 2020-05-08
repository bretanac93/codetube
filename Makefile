.PHONY: init all build test ping-mysql

init:
	test ! -f .env && cp .env.dist .env ;\
	test ! -f apps/main/resources/.env && cp apps/main/resources/.env.dist apps/main/resources/.env

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
