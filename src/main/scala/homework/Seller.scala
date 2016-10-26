package homework

import akka.actor.{Actor, Props}
import akka.event.LoggingReceive

import scala.concurrent.duration._
import scala.language.postfixOps

class Seller(val id: Int, auctionSearchPath:String, auctionTitles: List[String]) extends Actor {

  val auctionSearch = context.actorSelection(auctionSearchPath)

  for { auctionTitle <- auctionTitles } {
    val auction = context.actorOf(Props(Auction(auctionTitle, 20 seconds, 10 seconds)))
    auctionSearch ! AuctionSearch.AddAuction(auctionTitle, auction)
  }

  override def receive: Receive = LoggingReceive {

    case msg:Auction.AuctionFinished => {
      println(s"I sell ${msg.title} price ${msg.price} buyer ${msg.buyer}")
    }
  }
}
