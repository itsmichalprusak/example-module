# example-module

Example module boilerplate for [Kyoko Discord bot](https://github.com/KyokoBot/Kyoko).

## How to compile?

1. Clone main Kyoko repository.

```shell
$ git clone https://github.com/KyokoBot/Kyoko
```

2. Navigate into it and clone module repo which will be automatically detected and compiled by build system.

```shell
$ cd Kyoko
$ git clone https://github.com/KyokoBot/example-module
```

3. Compile the module

```shell
$ ./gradlew assemble                # if you want full build (core + all modules)
$ ./gradlew example-module:assemble # if you want single module build
```

4. Copy the `example-module.jar` file into `modules` directory of your running instance and restart or dynamically load
it (look at the bottom of readme).

## Creating a module for Kyoko

1. Create new directory in Kyoko's build tree

```shell
$ mkdir mymodule
$ cd mymodule
```

2. Create build.gradle, source tree, plugin.json and KyokoModule files (for detection by build system)

```shell
$ touch KyokoModule
$ mkdir src
$ mkdir src/main
$ mkdir src/main/java
$ mkdir src/main/resources
```

**build.gradle**:

```gradle
dependencies {
    compile project(":core") // core dependency
    compile "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version" // (optional) if you want to write in kotlin
}

jar {
    archiveName = "${project.name}.jar"
    destinationDir project(":core").file('../deploy/modules') // put it into modules directory
}
```

**src/main/resources/plugin.json**:

```json
{
  "name": "mymodule",
  "main": "com.github.gabixdev.mymodule.Module"
}
```

`name` - Module name, should be lowercase and not containing spaces or other characters

`main` - Main module class implementing [KyokoModule](https://github.com/KyokoBot/Kyoko/blob/kyoko-v2/core/src/main/java/moe/kyokobot/bot/module/KyokoModule.java) interface.

3. Go to IDE and create main module class

**Module.java**

```java
package com.github.gabixdev.mymodule;

import com.github.gabixdev.mymodule.commands.*;
import com.google.inject.Inject;
import moe.kyokobot.bot.command.Command;
import moe.kyokobot.bot.manager.CommandManager;
import moe.kyokobot.bot.module.KyokoModule;

import java.util.ArrayList;

public class Module implements KyokoModule {

    @Inject
    private CommandManager commandManager;

    private ArrayList<Command> commands;

    public Module() {
        commands = new ArrayList<>();
    }

    @Override
    public void startUp() {
        commands = new ArrayList<>();

        commands.add(new HelloCommand());

        commands.forEach(commandManager::registerCommand);
    }

    @Override
    public void shutDown() {
        commands.forEach(commandManager::unregisterCommand);
    }
}
```

**HelloCommand.java**

```java
package com.github.gabixdev.mymodule.commands;

import moe.kyokobot.bot.command.Command;
import moe.kyokobot.bot.command.CommandCategory;
import moe.kyokobot.bot.command.CommandContext;

public class HelloCommand extends Command {
    public HelloCommand() {
        name = "hello";
        category = CommandCategory.FUN;
    }

    @Override
    public void execute(CommandContext context) {
        context.send("Hello, " + context.getSender().getName());
    }
}
```

4. Compile it, and then go to `deploy/modules` and copy it into `modules` directory of your Kyoko instance.

```shell
$ # navigate back to build root
$ cd ..
$ ./gradlew mymodule:assemble
```

5. Restart Kyoko or go to Discord and load your own module using `modules` (`kd!` is default developer prefix) command.

```
kd!modules load modules/mymodule.jar
kd!modules start mymodule
```

6. Test it!

```
ky!hello
```

![hello example](https://puu.sh/BxU5m/5ffbea9b51.png)
