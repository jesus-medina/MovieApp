package com.backbase.assignment.domain

sealed class DomainGenre {
    object Action : DomainGenre()
    object Adventure : DomainGenre()
    object Animation : DomainGenre()
    object Comedy : DomainGenre()
    object Crime : DomainGenre()
    object Documentary : DomainGenre()
    object Drama : DomainGenre()
    object Family : DomainGenre()
    object Fantasy : DomainGenre()
    object History : DomainGenre()
    object Horror : DomainGenre()
    object Music : DomainGenre()
    object Mystery : DomainGenre()
    object Romance : DomainGenre()
    object ScienceFiction : DomainGenre()
    object TVMovie : DomainGenre()
    object Thriller : DomainGenre()
    object War : DomainGenre()
    object Western : DomainGenre()
    object Unknown : DomainGenre()
}
