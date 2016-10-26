package homework

import akka.actor.{Actor, ActorRef, Props}
import akka.event.LoggingReceive

import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps

class AuctionService extends Actor {

  import AuctionService._

  import scala.concurrent.duration._

  override def receive: Receive = LoggingReceive {
    case Init =>

      val auctionSearchName: String = "auctionSearch"
      val auctionSearch = context.system.actorOf(Props(new AuctionSearch()), auctionSearchName)
      auctionSearch.path

      for {i <- 0 until 3} {
        context.system.actorOf(Props(new Seller(i, s"../$auctionSearchName", auctionTitles.slice(3*i, (3*i)+3))))
      }

      for {i <- 0 until 10} {
        val buyer = context.system.actorOf(Props(Buyer(i, s"../$auctionSearchName")))
        this.context.system.scheduler.scheduleOnce(3 seconds, self, StartBuyer(buyer))
      }


    case msg:StartBuyer => {
      msg.buyer ! Buyer.Init
    }

  }

}

object AuctionService {

  val auctionTitles: List[String] = List(
    "Programming in Scala",
    "Suzuki Swift sport",
    "Sony amplifier",
    "Macbook Pro 2016",
    "PS4 Black Edition",
    "iPhone 6s 64GB",
    "Aspirin C",
    "Black T-shirt",
    "Red shirt",
    "LED Lamp",
    "Nike air max 1",
    "My soul")

  case object Init
  case class StartBuyer(buyer: ActorRef)
}