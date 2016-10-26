package homework

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.TestKit
import org.scalatest.easymock.EasyMockSugar
import org.scalatest.{BeforeAndAfterAll, FunSuite, WordSpecLike}

import scala.concurrent.duration._
import scala.language.postfixOps

class AuctionSearchTest extends TestKit(ActorSystem("AuctionSystem"))
  with WordSpecLike with BeforeAndAfterAll with EasyMockSugar {

  override def afterAll(): Unit = {
    system.terminate
  }

  "AuctionSearch" must {
    "return empty list" in {
      val auctionSearch = system.actorOf(Props(new AuctionSearch()))
      auctionSearch.tell(AuctionSearch.SearchAuction("something"), testActor)
      expectMsg(1 seconds, AuctionSearch.SearchResult("something", List()))
    }

    "return single elem list" in {
      val auctionSearch = system.actorOf(Props(new AuctionSearch()))
      val auction = mock[ActorRef]
      auctionSearch ! AuctionSearch.AddAuction("title", auction)
      auctionSearch.tell(AuctionSearch.SearchAuction("title"), testActor)
      expectMsg(1 seconds, AuctionSearch.SearchResult("title", List(auction)))
    }

    "return right elem" in {
      val auctionSearch = system.actorOf(Props(new AuctionSearch()))
      val auction1 = mock[ActorRef]
      val auction2 = mock[ActorRef]
      auctionSearch ! AuctionSearch.AddAuction("title1", auction1)
      auctionSearch ! AuctionSearch.AddAuction("title2", auction2)
      auctionSearch.tell(AuctionSearch.SearchAuction("title2"), testActor)
      expectMsg(1 seconds, AuctionSearch.SearchResult("title2", List(auction2)))
    }
  }

}
