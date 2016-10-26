package homework

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, WordSpecLike}

class SellerTest extends TestKit(ActorSystem("AuctionSystem"))
  with WordSpecLike with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    system.terminate
  }

  "Seller" must {
    "add auctions to AuctionService" in {
      val testActorPath = testActor.path.toSerializationFormat
      val seller = system.actorOf(Props(new Seller(1, testActorPath, List("a1", "a2"))))
      expectMsgPF() { case AuctionSearch.AddAuction("a1", _) => true }
      expectMsgPF() { case AuctionSearch.AddAuction("a2", _) => true }
    }
  }

  "Seller2" must {
    "add auctions to AuctionService" in {
      val test = TestProbe()
      val testActorPath = test.ref.path.toSerializationFormat
      val seller = system.actorOf(Props(new Seller(1, testActorPath, List("a1", "a2"))))
      test.expectMsgPF() { case AuctionSearch.AddAuction("a1", _) => true }
      test.expectMsgPF() { case AuctionSearch.AddAuction("a2", _) => true }
    }
  }
}
