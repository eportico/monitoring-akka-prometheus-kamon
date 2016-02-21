package com.michalplachta.shoesorter

import akka.actor.{Actor, Props}
import com.michalplachta.shoesorter.Messages._

class DecidersGuardian extends Actor {
  def receive = {
    case m: WhereShouldIGo =>
      val name = s"decider-${m.junction.id}"
      val actor = context.child(name) getOrElse context.actorOf(Props[SortingDecider], name)
      actor forward m
  }
}
