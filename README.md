# Unicorn Backend Web Application

This repository contains the three-layer backend web application (unicorn-simple), refactored to hexagonal architecture.

For example requests to the Web API, see LegControllerRequestsSimple.postman_collection.json

## Sources

- Get Your Hands Dirty on Clean Architecture, Tom Hombergs
- https://docs.aws.amazon.com/prescriptive-guidance/latest/hexagonal-architectures/welcome.html (ook in PDF formaat)
- Migrating your Spring Boot application to Java Modules by Jaap Coomans – YouTube (presentatie @ Devoxx)
- https://cleancoders.com/library/all (Clean Code series videos door Robert C. Martin, aka Uncle Bob)
- Agile Software Development: Principles, Patterns and Practices, Robert C. Martin (aka Uncle Bob)
- The Java Module System, Nicolai Parlog
- https://github.com/TomCools/jpms-hexagonal-architecture (voorbeeld project door Tom Cools)

### Klasse diagram Unicorn app

```mermaid
classDiagram
    direction LR
    UnicornController ..|> RestApiSpec
    ColoringService ..> Unicorn
    UnicornController ..> ColoringService
    ColoringService ..> UnicornRepo
    Unicorn ..> UnicornPart
    Leg --|> UnicornPart
    Unicorn *-- Leg
    ColoringService ..> SetColorDto
    class UnicornController {
        -ColoringService service
        +patchColor(jsonPatch)
    }
    class ColoringService {
        <<Service>>
        -UnicornRepo repo
        +setColor(setColorDto)
    }
    class SetColorDto {
        +Long unicornId
        +Long partId
        +Color color
    }
    class UnicornRepo {
        <<Interface>>
        +find(id)
        +save(unicorn)
    }
    class Unicorn {
        -List parts
        +getParts()
    }
    class UnicornPart {
        <<Abstract>>
        -Long id
        -Color color
        +setColor(color)*
    }
    class Leg {
        -color
        +setColor(color)
    }
```

### Klasse diagram test-per-klasse

```mermaid
classDiagram
    direction LR
    UnicornController ..|> RestApiSpec
    UnicornIT ..> RestApiSpec
    UnicornControllerTest --> UnicornController
    ColoringServiceTest ..> ColoringService
    UnicornTest ..> Unicorn
    LegTest ..> Leg
    ColoringService ..> Unicorn
    UnicornController ..> ColoringService
    ColoringService ..> UnicornRepo
    Unicorn ..> UnicornPart
    Leg --|> UnicornPart
    Unicorn *-- Leg
    ColoringService ..> SetColorDto
    class UnicornController {
        -ColoringService service
        +patchColor(jsonPatch)
    }
    class ColoringService {
        <<Service>>
        -UnicornRepo repo
        +setColor(setColorDto)
    }
    class SetColorDto {
        +Long unicornId
        +Long partId
        +Color color
    }
    class UnicornRepo {
        <<Interface>>
        +find(id)
        +save(unicorn)
    }
    class Unicorn {
        -List parts
        +getParts()
    }
    class UnicornPart {
        <<Abstract>>
        -Long id
        -Color color
        +setColor(color)*
    }
    class Leg {
        -color
        +setColor(color)
    }
    class UnicornControllerTest {
        +testPatchColor()
    }
    class ColoringServiceTest {
        +testSetColor()
    }
    class UnicornTest {
        +testGetLegs()
    }
    class LegTest {
        +testSetColor()
    }
    class UnicornIT {
        +testPatch()
    }
```

### Flow-chart test-per-klasse

```mermaid
flowchart LR
    UnicornIT --> ControllerPair
    subgraph ControllerPair
        direction TB
        UnicornControllerTest --> UnicornController
    end
    subgraph ServicePair
        direction TB
        ColoringServiceTest --> ColoringService
    end
    ControllerPair --> ServicePair
    ServicePair --> UnicornPair
    subgraph Domain Model
        direction LR
        UnicornPair --> LegPair
        subgraph UnicornPair
            direction TB
            UnicornTest --> Unicorn
        end
        subgraph LegPair
            direction TB
            LegTest --> Leg
        end

    end
    ServicePair --> H2
    classDef red stroke:#f00;
    class UnicornControllerTest red;
    class ColoringServiceTest red;
    class UnicornTest red;
    class LegTest red;
    classDef green stroke:#0f0
    class UnicornIT green;
    class H2 green;
```

### Klasse diagram testen-via-api

```mermaid
classDiagram
    direction LR
    ApiTest ..> ColoringApi
    ApiTest --> InMemoryUnicornRepo
    UnicornIT ..> RestApiSpec
    UnicornController ..|> RestApiSpec
    ColoringService ..> Unicorn
    UnicornController ..> ColoringService
    ColoringService ..> ColoringApi
    ColoringService ..> UnicornRepo
    InMemoryUnicornRepo ..> UnicornRepo
    Unicorn ..> UnicornPart
    Leg --|> UnicornPart
    Unicorn *-- Leg
    ColoringApi ..> SetColorDto
    class ApiTest {
        +canSetColor()
        +settingWrongColorFormatThrowsException()
        +onlySettingCuteColorsAllowed()
    }
    class UnicornController {
        -ColoringService service
        +patchColor(jsonPatch)
    }
    class ColoringService {
        <<Service>>
        -UnicornRepo repo
        +setColor(setColorDto)
    }
    class ColoringApi {
        <<Interface>>
        +setColor(setColorDto)
    }
    class SetColorDto {
        +Long unicornId
        +Long partId
        +Color color
    }
    class UnicornRepo {
        <<Interface>>
        +find(id)
        +save(unicorn)
    }
    class InMemoryUnicornRepo {
        -unicornMap
        +find(id)
        +save(unicorn)
    }
    class Unicorn {
        -List parts
        +getParts()
    }
    class UnicornPart {
        <<Abstract>>
        -Long id
        -Color color
        +setColor(color)*
    }
    class Leg {
        -color
        +setColor(color)
    }
    class UnicornIT {
        +testPatch()
    }
```

### Flow-chart testen-via-api

```mermaid
flowchart LR
    subgraph weblayer[Web-layer]
        direction TB
        UnicornIT --> UnicornController
    end
    subgraph applayer[Application-layer]
        subgraph domain[domain model]
            direction LR
            Unicorn --> Leg
        end
        ColoringService -. dependency .-> ColoringApi
    end
    subgraph persistencelayer[Persistence-layer]
        direction TB
        InMemoryUnicornRepo -. dependency .-> UnicornRepo
        H2 -. dependency .-> UnicornRepo
    end
    ApiTest --> applayer
    weblayer --> applayer
    ColoringService --> domain
    applayer --> persistencelayer
    classDef red stroke:#f00;
    classDef green stroke:#0f0;
    class ApiTest red;
    class UnicornIT green;
    class H2 green;
    class InMemoryUnicornRepo red;
    class ColoringApi red;
```