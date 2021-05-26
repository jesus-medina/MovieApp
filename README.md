# MovieApp

## Architecture

The app is created with a clean architecture approach. The main layers are Presentation, Domain and Data.

```puml
title MovieApp Architecture - Component Diagram

[Presentation] as P
[Domain] as DM
[Data] as DT

P -> DM
DM -> P
DM -> DT
DT -> DM

@enduml
```
