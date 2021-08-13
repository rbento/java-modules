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
$ java --list-modules
$ java --describe-module java.sql
$ java --show-module-resolution -p out -m modulea
```
