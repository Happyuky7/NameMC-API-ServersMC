# NameMC-API-ServersMC

Use of the NameMC API for Minecraft Servers.

### [Discord](https://discord.gg/3EebYUyeUX)
### [Github](https://github.com/Happyuky7/NameMC-API-ServersMC/)
### [Download 1.8 - 1.21.4](https://github.com/Happyuky7/NameMC-API-ServersMC/releases)

### [DEV] [NEW VERSION 2.0.0 (DEV-118) (RE-CODE)](https://github.com/Happyuky7/NameMC-API-ServersMC/releases/tag/2.0.0-DEV-118)

---

## Install 

- Download Lasted Plugin Version.
- - Move you plugin folder `/plugins`.
- Start or Restart your server.
- Join a plugin folder `/plugins/NameMCAPI`.
- Configure Server IP in `config.yml`.
<br></br>
- (Optional) Configure Save Data in `config.yml`.
  - You config `YAML` Required **Restart**.
  - You config `MongoDB` Required **Restart**.
<br></br>
- Restart Your Server.

**Note:** If you use any `DEV-VERSION` of the plugin, 
I **recommend** you to make a **backup** and **check** the **changelist** of 
the plugin to avoid problems with `config`, `messages`, `commands` or `other things`.

---

## Commands

- `/namemc` - Main Command.
- `/namemc reload` - Reload Plugin.
- `/namemc help` - View all commands and subcommands.

---

## Permissions

- `namemc.admin` - Permission to use all commands.
- `namemc.reload` - Permission to reload the plugin.

---

## Placeholder API

- `%namemc_ip%` - IP of the server.
- `%namemc_player%` - Player Name.
- `%namemc_uuid%`, `%namemc_uniqueid%`, `%namemc_playeruuid%` - Player UUID.
- `%namemc_vote_link%` - Vote Link.
- `%namemc_voted%` - Voted Player.
- `%namemc_vote_boolean%` - Boolean Voted Player. (True or False)
- `%namemc_reward%` - Reward Player.
- `%namemc_reward_boolean%` - Boolean Reward Player. (True or False)

---

## How To (Compiling From Source)

To compile NameMC-API-ServersMC, you need JDK17, git, bash, maven.

Clone the repository with the following command:
```bash
$ git clone https://github.com/Happyuky7/NameMC-API-ServersMC.git
```

Once downloaded in the git terminal use the following command:

```bash
$ cd NameMC-API-ServersMC/Code/
```

Now inside the directory you execute the following maven command:

```bash
$ mvn clean install
```

Then you will find the compiled in the following folder

```bash
NameMC-API-ServersMC/Code/target/
```

---

## Contributors

- [Happyuky7](https://github.com/Happyuky7) - Main Developer, Maintainer, Translator (English, Spanish, Japanese, Chinese Simplified).
- [InfoBlock](https://github.com/InfoBlock) - Translator German.
- [staFF6773](https://github.com/staFF6773) - Translator Spanish (Spain).
- [bobhenl](https://github.com/bobhenl) - Tester.

---

## Join us

* Feel free to open a PR! We accept contributions.
* [Discord](https://discord.gg/3EebYUyeUX)

---

## Aditional Information

[My website](https://happy7.xyz), My website.

[Discord](https://discord.gg/3EebYUyeUX), Support My Server Discord.


---

© Copyright Happyuky7 2017-2025 ©
RIGHTS RESERVED

## Special Thanks To

![IntelliJ IDEA logo](https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA_icon.png?size=100px)
![IntelliJ IDEA logo](https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA.png)
![Azul Java logo](https://www.azul.com/wp-content/themes/azul/dist/img/logo.svg)

[IntelliJ IDEA](https://www.jetbrains.com/idea/), Code editor for Java and other programming languages and programs.

[Azul Java](https://www.azul.com/) The world’s largest commercial provider of OpenJDK
