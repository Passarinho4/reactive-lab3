package homework

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, WordSpecLike}

import scala.concurrent.duration._
import scala.language.postfixOps

class AuctionTest extends TestKit(ActorSystem("AuctionSystem"))
  with WordSpecLike with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    system.terminate
  }

  "An auction" must {
    "increments it's price" in {
      val auction = system.actorOf(Props(Auction("id", 1 seconds, 2 seconds)))
      auction ! Auction.Bid(10, testActor)
      auction ! Auction.Bid(20, testActor)
      expectMsg(4 seconds, Auction.YouWonTheAuction(20))
    }
  }


}
