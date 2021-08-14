# java-modules

A sample project to test the Java Platform Modules System feature.

### Notes
---

###### General

```
[open] module <module_name> {
	exports <package>;
	opens <package>;
	requires <module_name>;
}
```

- An open module implicitly opens all packages.
- All packages are strongly encapsulated by default.
- The keyword `exports` creates the module API by exposing packages to other modules.
- The keyword `requires` indicates an explicit direct dependency on another module. 
- The keyword `opens` allows packages to be exposed at runtime while maintaining strongly encapsulation at compile time.
- The `requires transitive` is used to implicitly declare dependencies on modules used by a given module implementation without forcing users of such module to explicitly declare all its dependencies.  
Ex: The `java.sql` module transitively requires `java.logging`, which is used by the Driver class. In this case, users of `java.sql` do not need to explicitly declare the dependency on `java.logging`.


###### Qualified Exports

```
module util {
	exports com.company.util to modulea;
}
```

- If `modulea` has dependency on `util`, it will be able to access the `com.company.util` package. Other modules depending on `util` won't have access to `com.company.util` package.


###### Qualified Opens

```
module util {
	exports com.company.util to modulea, moduleb;
}
```

- It means that `modulea` and `moduleb` can reflectively access classes in the `com.company.util` package should they depend on the `util` module.

###### Module Resolution

- Modules are resolved starting at the `root module`.
- If a required package is not present in a module a `java.lang.module.FindException` is thrown.
- Only one module with a given name can be declared in the same directory in a module path.
- Packages must uniquely belong to a single module otherwise a `java.lang.LayerInstantiationException` is thrown.


###### Packaging Modules

- The presence of `module-info.class` at the root of a jar file is what makes a `jar` file into a modular jar.

```
$ jar --create --file=modules/modulea.jar -C out .
```

- A module version can be defined but has no special meaning. Just a convenience to add the version name to the generated jar file and modules cannot be required with a specified version.

```
$ jar --create --file=modules/modulea.jar --module-version=1.0.0 -C out 
```

See also `jmod` and `jlink`.

###### Inspecting a Modular JAR

```
$ jar --describe-module --file=modules/modulea.jar

```

### Build
---

- Explicitly building all modules.

```
javac -d out --module-source-path src -m greeter.cli,greeter.hello
```

- Implicitly building dependent modules.

```
javac -d out --module-source-path src -m greeter.cli
```

### Run
---

```
java -p out -m greeter.cli/greeter.cli.Main
```

### Related Commands
---

```
$ jar --help
$ java --list-modules
$ java --describe-module java.sql
$ java --show-module-resolution -p out -m modulea
```
