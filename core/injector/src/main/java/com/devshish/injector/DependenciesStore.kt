package com.devshish.injector

/**
 * Marker interface for dependencies storage
 */
interface DependenciesStore<DEP : BaseFeatureDependencies> {
    var dependencies: DEP
}
