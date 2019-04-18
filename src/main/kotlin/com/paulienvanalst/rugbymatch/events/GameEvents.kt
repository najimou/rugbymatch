package com.paulienvanalst.rugbymatch.events

import com.paulienvanalst.rugbymatch.game.LineOut
import com.paulienvanalst.rugbymatch.game.Scrum
import com.paulienvanalst.rugbymatch.game.SetPiece
import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.ApplicationEvent

sealed class SetPieceEvent(source: Any, val setPiece: SetPiece, val winningTeam: TeamName) : ApplicationEvent(source)

class ScrumWasPlayed(source: Any, scrum: Scrum, winningTeam: TeamName) : SetPieceEvent(source, scrum, winningTeam)
class LineOutWasPlayed(source: Any, lineOut: LineOut, winningTeam: TeamName) : SetPieceEvent(source, lineOut, winningTeam)

fun List<SetPieceEvent>.wonBy(team: TeamName) = this.filter { it.winningTeam == team }
fun List<SetPieceEvent>.lostBy(team: TeamName) = this.filter { it.winningTeam != team }

fun List<SetPieceEvent>.lineOutEvents() = this.filter { it is LineOutWasPlayed }
fun List<SetPieceEvent>.scrumEvents() = this.filter { it is ScrumWasPlayed }
