# VineryJS

The mod simply allows you to register new wine items and modify existing ones!


Modrinth: https://modrinth.com/mod/vineryjs

CurseForge: https://www.curseforge.com/minecraft/mc-mods/vineryjs


Modifying existing wines:

```
VineryJS.modifyWine(event => {
    event.modify(
        'vinery:mellohi_wine',          // item id
        'alexsmobs:poison_resistance',  // effect id
        2800,                           // duration in ticks
        0,                              // amplifier
        true                            // does it scale with age?
    )
})
```



Registering new wines:

In a future update this will be fully working, however it is giving me issues. You may use it, however placing down wines crashes the game, so it is disabled by default. If you wish to mess with the placing of them, there is a config option to enable it, and you'd use ".allowPlacement()".

Some extra functions include: .itemTexture, .blockTexture, .itemParentModel, and .blockParentModel

```
VineryJS.registerWine(event => {
    event.create('kubejs:test_wine')
        .wineName('TEST')                       // display name
        .effect('minecraft:strength', 3600, 1)  // effect id, duration in ticks, amplifier
        .bottleSize('big')                      // size of the bottle
        .scaleWithAge()                         // does it scale with age?
})
```
