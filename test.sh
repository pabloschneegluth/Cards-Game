#!/bin/bash

error() {
  echo "!! FAILED $*"
  exit 1;
}

rm -fr $(find src -name 'Post_*_Test.*')
yarn                                          || error "yarn"
CI=1 yarn create-tests                        || error "yarn create-tests"
./mvnw test                                   || error "./mvwn test"
CI=1 yarn test --coverage --testTimeout 20000 || error "yarn test"
CI=$CI yarn test-coverage                     || error "yarn test-coverage"
yarn build                                    || error "yarn build"
./mvnw compile                                || error "./mvwn compile"

