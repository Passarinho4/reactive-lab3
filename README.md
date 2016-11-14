# reactive-lab3
Dr Katarzyna Rycerz 26/40 
-brak testów relacji rodzic-dziecko (Auction-Seller)
-brak podpunktu 3 wgl. 


(20 pkt) Prosze dodac do systemu aukcyjnego aktorow Seller i AuctionSearch
Seller wystawia kilka aukcji o roznych tytulach (przykladowo "Audi A6 diesel manual"). Seller tworzy aktorów aukcji (można np. przekazywac przez konstruktor liste tytulów aukcji, ktore ma wystawić).
AuctionSearch to aktor, w którym można wyszukiwać aukcje. Zapytanie wyszukiwania może skladac sie z jednego slowa i jesli slowo to wystepuje w tytule aukcji, to aukcja pasuje do zapytania. (Wynikiem zapytania jest lista identyfikatorów ActorRef aukcji).
Kazda aukcja musi rejestrować sie w AuctionSearch. Prosze wykorzystać mechanizm Actor Selection do wyszukiwania aktora AuctionSearch.
Aktorzy Buyer powinni korzystac z wyszukiwania przez AuctionSearch aby uzyskac referencje do iteresujacych ich aukcji.
po zakończeniu aukcji odpowiedni aktor Auction wysyła notyfikację do swojego aktora Seller .

(10 pkt) Wykorzystując Akka TestKit proszę napisac testy dla aktorów Auction, AuctionSearch i Seller.
Aby przetestować wymianę komunikatów między aktorami należy skorzystać z TestProbe.
Testowanie relacji rodzic-dziecko (potrzebne np. dla relacji Seller - Auction)

(10 pkt) Rozszerzenie systemu:
Proszę rozszerzyć Aktora Buyer o nowe zachowanie (stan), w którym zapisuje się na notyfikacje, gdy ktoś przebije jego aktualna ofertę i samemu przebija ofertę, jeśli cena nie przekroczyła jego kwoty maksymalnej. Prosze rozszerzyć Aktora Auction o potrzebne wsparcie dla takiego zachowania.
Proszę napisać testy dla tego rozszerzenia (tj. dla aktorów Buyer oraz Auction).
