/**
 * The context package manually sets up and injects all contexts that do not need Spring DI.
 * This way, both the application and the tests are faster. Especially tests that only need these contexts,
 * are much faster, facilitating TDD.
 */
package nl.pancompany.unicorn.context;