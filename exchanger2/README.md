# Project

This project offered an api that helps to exchange an amount between different currencies (Spring boot)

## How to hit the ground running




### Database

* it use h2 in memory

### Tests

* Each implemented story should have acceptance test covering all accepptance criterias provided in spec.
* Run `autotest` to run test instantly, triggered by project changes. Autotest does not replace whole test suite run after pull/push to origin flow.

### Security

* Mass-assignment is disabled by default. All public fields accessible by user should be listed in attr_accessible class method of model.

### Git

* [Rebase](http://www.randyfay.com/node/91) is prefered over merge. There should not be any merge commits.
* Combine commits with [git squash](https://ariejan.net/2011/07/05/git-squash-your-latests-commits-into-one) to have useful history.

## Definition of Done

These criteria need to be fulfilled for the story to be marked as "resolved" in the project management application.

* The story is implemented and tests are created.
* The story passes the acceptance tests.
* The story does not increase the "technical debt".
* The story was integrated into the overall system.
* The story was successfully deployed to the staging environment.