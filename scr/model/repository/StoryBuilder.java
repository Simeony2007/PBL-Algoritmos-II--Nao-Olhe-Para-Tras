package scr.model.repository;

import scr.model.service.Chapter;
import scr.model.service.Condition;
import scr.model.service.Npc;
import scr.model.service.Orfeu;
import scr.model.service.Scene;

import java.util.ArrayList;


public class StoryBuilder {
    public static Object BuildGame;

    public static Orfeu getOrfeu(){
        Orfeu orfeu = new Orfeu(1, 0, 0, 0);
        return orfeu;
    }

    public static ArrayList<Npc> GetNpcs(){
        ArrayList<Npc> npcs = new ArrayList<>();

        Npc charon = new Npc("Charon", 0);
        Npc eurydice = new Npc("Eurydice", 0);
        Npc sisyphus = new Npc("Sisyphus", 0);

        npcs.add(charon);
        npcs.add(eurydice);
        npcs.add(sisyphus);

        return (npcs);
    }

    public static ArrayList<Chapter> getChapters(){
        ArrayList<Chapter> chapters = new ArrayList<>();
        chapters.add(BuildChapter1());
        chapters.add(BuildChapter2());
        chapters.add(BuildChapter3());
        chapters.add(BuildChapter4());
        chapters.add(BuildChapter5());
        chapters.add(BuildChapter6());
        chapters.add(BuildChapter7());
        chapters.add(BuildChapter8());
        chapters.add(BuildChapter9());
        chapters.add(BuildChapter10());
        return chapters;
    }


    public static Chapter BuildChapter1(){ 
        Chapter chapter = new Chapter("Capítulo 1 - A Entrada do Submundo");

        Scene scene1 = new Scene("Introdução"); 
        Scene scene2 = new Scene("Encontro com Cérbero"); 
        Scene scene2a = new Scene("s2a_cerberus", "Margem do Rio"); 
        Scene scene2b = new Scene("s2b_cerberus", "Margem do Rio"); 
        Scene scene2c = new Scene("s2c_cerberus", "Margem do Rio"); 
        Scene scene3 = new Scene("Margem do Rio"); 
        Scene scene4 = new Scene("Conversa com Alma"); 
        Scene scene4a = new Scene("s4a_soul", "Conversa com Caronte"); 
        Scene scene4b = new Scene("s4b_soul", "Conversa com Caronte"); 
        Scene scene5 = new Scene("Conversa com Caronte"); 
        Scene scene5a = new Scene("s5a_charon", "Barca de Caronte"); 
        Scene scene5b = new Scene("s5b_charon", "s5c_charon"); 
        Scene scene5c = new Scene("s5c_charon", "Barca de Caronte"); 
        Scene scene6 = new Scene("Barca de Caronte"); 
        Scene scene6a = new Scene("s6a_charon_boat", "s7_end_chapter"); 
        Scene scene6b = new Scene("s6b_charon_boat", "s7_end_chapter"); 
        Scene scene6c = new Scene("s6c_charon_boat", "s7_end_chapter"); 
        Scene scene7 = new Scene("s7_end_chapter", null);

        // Cena 1 - Introdução 
        scene1.addNewDialogue("Orfeu, filho da musa Calíope, era considerado o maior bardo de toda a Grécia. Com a harpa de Apolo em mãos, ele viajou com os Argonautas, entoando canções que acalmavam mares e feras."); 
        scene1.addNewDialogue("Sua vida ganhou verdadeiro sentido ao se casar com a bela ninfa Eurídice. No entanto, a alegria durou pouco. Logo após a cerimônia, enquanto fugia de um perseguidor invejoso e cheio de ódio, ela foi picada por uma víbora e seu espírito foi levado para o reino dos mortos."); 
        scene1.addNewDialogue("Consumido pelo luto, Orfeu toma uma decisão impensável: descer ao próprio Submundo, desafiar as leis da vida e da morte, e trazer sua amada de volta para a luz."); 
        scene1.addNewDialogue("O que mais motiva os passos de Orfeu em direção ao abismo?");

        scene1.addNewChoice("Seu peito queima com um amor que transcende a mortalidade. Você avança movido pela convicção de que nem mesmo Hades pode separar duas almas gêmeas.", "Encontro com Cérbero", 0, 0, 2, 0, 0); 
        scene1.addNewChoice("Cada nota que toca é um lamento lúgubre. A dor de não tê-la ao seu lado é um peso esmagador que o arrasta naturalmente para as sombras.", "Encontro com Cérbero", 0, 0, 0, 0, 2); 
        scene1.addNewChoice("Como os deuses permitiram tamanha injustiça? Sua música agora é um hino de fúria contra o destino cruel, e você não aceitará um 'não' como resposta.", "Encontro com Cérbero", 0, 0, 2, 0, 0);

        // Cena 2 - Confronto com Cérbero 
        scene2.addNewDialogue("A jornada o leva até o sombrio Cabo Tênaro, onde o ar se torna gélido e com cheiro de enxofre. Diante da caverna profunda, uma criatura aterrorizante barra definitivamente o seu caminho."); 
        scene2.addNewDialogue("É Cérbero, o cão gigante de três cabeças, rosnando e babando, pronto para devorar qualquer ser vivo que ouse adentrar os domínios de seu mestre. Orfeu, ciente de que armas mortais são inúteis aqui, não puxa uma espada, mas sim as cordas douradas de sua harpa."); 
        scene2.addNewDialogue("Qual música Orfeu tocará para lidar com o guardião infernal?"); 
        scene2.addNewChoice("Música sobre o descanso e o acalento da natureza", "s2a_cerberus", 0, 0, 1, 0, 0); 
        scene2.addNewChoice("Música sobre o peso da perda", "s2b_cerberus", 0, 0, 0, 0, 1); 
        scene2.addNewChoice("Música sobre guerra", "s2c_cerberus", 0, 0, 0, 1, 0);

        // Cenas 2a, 2b e 2c - Resultados das escolhas com o Cérbero 
        scene2a.addNewDialogue("As notas fluem suaves como a brisa da primavera, lembrando o cão dos campos verdejantes. As três cabeças se acalmam, deitando no chão e caindo em um sono sereno, abrindo caminho."); 
        scene2b.addNewDialogue("As notas graves e melancólicas carregadas do peso de um luto insuportável fazem Cérbero soltar ganidos lúgubres pelas três bocas, chorando em sintonia com a dor do bardo enquanto este contorna o guardião cabisbaixo, determinado a transformar a própria tragédia no caminho para resgatar sua amada."); 
        scene2c.addNewDialogue("O dedilhado agressivo e vibrante ressoa como o estalo do trovão, intimidando a besta colossal que dá passos para trás e se curva em submissão involuntária, abrindo passagem para Orfeu avançar com passos firmes em direção às profundezas do reino dos mortos.");

        // Cena 3 - Margem do Rio Estige 
        scene3.addNewDialogue("A escuridão engole Orfeu enquanto ele desce pelas entranhas da terra. Em pouco tempo, ele chega a uma costa sombria, margeada por um rio lamacento e coberta por uma névoa densa."); 
        scene3.addNewDialogue("O local está lotado de almas translúcidas humanas, que vagam sem rumo murmurando lamentos incessantes. Atracado na água escura do rio, um pequeno barco de madeira balança levemente, guiado por um homem velho, de pele cinzenta, que aguarda impassível."); 
        scene3.addNewDialogue("O que você faz ao observar a margem do rio dos mortos?");

        scene3.addNewChoice("Se aproximar do Caronte", "Conversa com Caronte", 0, 0, 0, 0, 0); 
        scene3.addNewChoice("Interagir com uma das almas", "Conversa com Alma", 0, 0, 0, 0, 0);

        // Cena 4 - Conversa com alma 
        scene4.addNewDialogue("Você se aproxima de uma sombra curvada que chora compulsivamente na beira do rio, estendendo suas mãos pálidas para as águas negras."); 
        scene4.addNewDialogue("Orfeu: Quem é você, e por que chora mais alto que o próprio correr destas águas?"); 
        scene4.addNewDialogue("Alma Esquecida: Sou apenas um pobre coitado, bardo... Estou vagando por esta margem há um século. Minha família me enterrou às pressas, sem a honra de uma moeda sob a língua."); 
        scene4.addNewDialogue("Alma Esquecida: Dizem que, de vez em quando, algumas moedas extras perdidas aparecem pelo chão de terra da margem, trazidas pelas correntes. Mas meus olhos já estão opacos... Eu nunca achei uma sequer."); 
        scene4.addNewDialogue("Como você reage ao lamento centenário dessa alma?");

        scene4.addNewChoice("Oferecer seu único óbolo","s4a_soul", "Charon", -1, 1, 0, 0, 0); 
        scene4.addNewChoice("Deixar a Alma para trás", "s4b_soul", 0, 0, 0, 0, 0);

        // Cena 4a e 4b 
        scene4a.addNewDialogue("Você coloca uma moeda brilhante na mão espectral. A alma chora lágrimas de gratidão e desaparece rumo ao barco. Ao longe, o velho barqueiro observa seu ato com um olhar intrigado."); 
        scene4b.addNewDialogue("Você se afasta com pesar, deixando a alma continuar sua eterna e frustrante busca no barro.");

        // Cena 5 - Conversa com Caronte 
        scene5.addNewDialogue("Você finalmente para diante do barqueiro do Submundo. Caronte o observa de cima a baixo com olhos ocos que brilham com uma luz esverdeada e avarenta sob o capuz esfarrapado."); 
        scene5.addNewDialogue("Caronte: Mais um espírito choroso para encher meu barco... Não, espere. Há sangue quente correndo sob essa pele."); 
        scene5.addNewDialogue("Orfeu: Preciso cruzar este rio. Tenho assuntos a tratar no reino de Hades, no outro lado."); 
        scene5.addNewDialogue("Caronte: Assuntos de vivos não me dizem respeito, bardo. Meu único negócio aqui é a cobrança. A regra é imutável: eu espero a moeda, o passageiro paga a moeda. Enquanto houver pagamento, eu não me importo se você respira ou não."); 
        scene5.addNewDialogue("Como você negociará a travessia com o barqueiro?");

        scene5.addNewChoice("Pagar a moeda", "s5a_charon", -1, 0, 0, 0, 0); 
        scene5.addNewChoice("Tentar convencer o Caronte a deixá-lo passar sem pagar", "s5b_charon" , "Charon", 0, 0, 0, 0, 0); 
        scene5.addNewChoice("Voltar para a margem", "s5c_charon", 0, 0, 0, 0, 0);

        // Cena 5a, 5b e 5c 
        scene5a.addNewDialogue("Sem hesitar, você coloca o óbolo na mão esquelética de Caronte. Ele sente o peso do metal, dá um sorriso desdentado e aponta com a cabeça para que você suba na barca."); 
        scene5b.addNewDialogue("Orfeu: Você não sabe quem eu sou? Minha música vale mais que todos os tesouros de Hades! Me deixe passar!"); 
        scene5b.addNewDialogue("Caronte: E minha paciência vale menos do que a poeira que você pisa. Sem prata, sem viagem. Saia daqui, mortal insolente!"); 
        scene5c.addNewDialogue("Desolado por não ter como pagar, você se afasta do píer. Você chora e passa horas cavando a terra fria, até que o brilho de um óbolo esquecido reflete na penumbra. Com ele em mãos, você retorna à barca e garante sua passagem.");

        // Cena 6 - Conversa na Barca 
        scene6.addNewDialogue("A madeira podre range sob seus pés enquanto a barca corta as águas densas do submundo. O silêncio é pesado, quebrado apenas pelo som do remo batendo contra a correnteza."); 
        scene6.addNewDialogue("Caronte: Não costumo receber passageiros com um coração batendo no peito. A maioria dos mortais foge de fininho do Submundo, não entra nele marchando."); 
        scene6.addNewDialogue("Caronte: Diga-me, vivo... O que exatamente te arrastou para a terra dos mortos?"); 
        scene6.addNewDialogue("O que você responde às indagações de Caronte?");

        scene6.addNewChoice("Não faça perguntas, apenas me leve em frente", "s6a_charon_boat","Charon", 0, -1, 0, 0, 0); 
        scene6.addNewChoice("Desejo rever o amor da minha vida", "s6b_charon_boat","Charon", 0, 0, 0, 0, 0); 
        scene6.addNewChoice("Não consigo viver sem ela", "s6c_charon_boat","Charon", 0, 0, 0, 0, 0);

        // Cena 6a, 6b e 6c 
        scene6a.addNewDialogue("Caronte aperta o remo com força, irritado com a sua hostilidade."); 
        scene6a.addNewDialogue("Caronte: Tolo arrogante."); 
        scene6a.addNewDialogue("Caronte: Hades vai mastigar sua insolência."); 
        scene6b.addNewDialogue("A postura tensa do barqueiro relaxa um pouco."); 
        scene6b.addNewDialogue("Caronte: Amor... O veneno dos vivos."); 
        scene6c.addNewDialogue("Você desvia o olhar, encarando seu próprio reflexo distorcido na água negra."); 
        scene6c.addNewDialogue("Caronte solta um suspiro rouco, há muito acostumado com o desespero, mas levemente impressionado pela profundidade da sua dor.");

        // Cena 7 
        scene7.addNewDialogue("Caronte: Escute bem, vivo. Minha jurisdição é limitada pelas águas. Posso levá-lo apenas uma região por vez. Se deseja se embrenhar mais a fundo nos domínios de Hades, precisará de mais moedas para o resto do trajeto.");

        chapter.newScene(scene1); 
        chapter.newScene(scene2); 
        chapter.newScene(scene2a); 
        chapter.newScene(scene2b); 
        chapter.newScene(scene2c); 
        chapter.newScene(scene3); 
        chapter.newScene(scene4); 
        chapter.newScene(scene4a); 
        chapter.newScene(scene4b); 
        chapter.newScene(scene5); 
        chapter.newScene(scene5a); 
        chapter.newScene(scene5b); 
        chapter.newScene(scene5c); 
        chapter.newScene(scene6); 
        chapter.newScene(scene6a); 
        chapter.newScene(scene6b); 
        chapter.newScene(scene6c); 
        chapter.newScene(scene7);

        return chapter; 
    }

    public static Chapter BuildChapter2(){
        Chapter chapter = new Chapter("Capítulo 2 - Campos de Asfódelos");

        Scene scene1 = new Scene("s1_asphodel", "Alma do Marinheiro");
        Scene scene2 = new Scene("Alma do Marinheiro");
        Scene scene2a = new Scene("s2a_sailor", "Fã de Orfeu");
        Scene scene2b = new Scene("s2b_sailor", "Fã de Orfeu");
        Scene scene2c = new Scene("s2c_sailor", "Fã de Orfeu");
        Scene scene3 = new Scene("Fã de Orfeu");
        Scene scene3a = new Scene("s3a_fan", "Ataque das Keres");
        Scene scene3b = new Scene("s3b_fan", "Ataque das Keres");
        Scene scene3c = new Scene("s3c_fan", "Ataque das Keres");
        Scene scene4 = new Scene("Ataque das Keres");
        Scene scene4a = new Scene("s4a_keres", "Retorno de Caronte");
        Scene scene4b = new Scene("s4b_keres", "Retorno de Caronte");
        Scene scene4c = new Scene("s4c_keres", "Retorno de Caronte");
        Scene scene5 = new Scene("Retorno de Caronte");
        Scene scene5a = new Scene("s5a_charon", "s6_end_chapter");
        Scene scene5b = new Scene("s5b_charon", "s6_end_chapter");
        Scene scene6 = new Scene("s6_end_chapter", null);

        // Cena 1 - Campos de Asfódelo (Introdução)
        scene1.addNewDialogue("O barco de Caronte se afasta lentamente, deixando Orfeu nas margens desoladas dos Campos de Asfódelo. Diante dele se estende uma planície cinzenta e infinita, onde flores pálidas e murchas cobrem o chão coberto de pó.");
        scene1.addNewDialogue("Milhares de almas vagam sem rumo neste limbo apático, murmurando palavras ininteligíveis. Não há grandes punições aqui, mas também não há alegria; apenas uma existência monótona e esquecida, suspensa na eternidade de Hades.");
        scene1.addNewDialogue("Orfeu sabe que, para prosseguir sua descida pelo Submundo e cruzar a próxima barreira do rio Estige, precisará convencer o barqueiro novamente. O bardo aperta sua harpa divina contra o peito, ciente de que precisará encontrar mais um óbolo neste deserto espiritual.");

        // Cena 2 - A alma de um marinheiro
        scene2.addNewDialogue("Caminhando pela névoa densa, Orfeu percebe uma figura robusta, de braços cruzados, observando as águas paradas ao longe com uma saudade profunda. A alma usa trapos que um dia foram roupas de um navegador.");
        scene2.addNewDialogue("Ao ouvir os passos firmes de Orfeu, o espírito se vira bruscamente. Seus olhos translúcidos se arregalam ao notar as cores vivas e o calor emanando do corpo do bardo. É uma visão raríssima.");
        scene2.addNewDialogue("Marinheiro: Pelos deuses esquecidos... Você respira! Faz tanto tempo que não vejo a vida pulsando dessa forma. Diga-me, jovem vivo... Como estão os oceanos?");
        scene2.addNewDialogue("Marinheiro: Eu fui um grande capitão nos mares Egeus, mas a névoa deste lugar está devorando minha mente. Eu... Eu não consigo mais lembrar da cor do mar, nem do som das ondas. Por favor, me traga uma lembrança.");
        scene2.addNewDialogue("Como Orfeu responderá ao apelo doloroso do velho lobo do mar?");

        scene2.addNewChoice("Música vibrante sobre o mar e a brisa salgada", "s2a_sailor", 0, 0, 0, -1, 0);
        scene2.addNewChoice("Lamentar a perda das memórias e a tragédia do marinheiro", "s2b_sailor", 0, 0, 0, 0, 0);
        scene2.addNewChoice("Ignorar sua prece nostálgica e pedir informações", "s2c_sailor", "Charon", 0, -1, 0, 0, 0);

        // Cenas 2a, 2b e 2c - Resultados das escolhas com o Marinheiro
        scene2a.addNewDialogue("A melodia traz o cheiro imaginário de sal e a visão de águas cristalinas. O marinheiro chora de alegria.");
        scene2a.addNewDialogue("Marinheiro: Obrigado! E se esse velho pode te ajudar em sua jornada, tem monstros voadores perigosos nesses campos, eles só recuam se você revirar seu ataque.");

        scene2b.addNewDialogue("Orfeu canta uma balada suave e dolorosa sobre como tudo no mundo dos vivos acaba desaparecendo nas areias do tempo. O marinheiro suspira, aceitando o abraço frio da eternidade e desaparecendo lentamente na multidão silenciosa de Asfódelo.");

        scene2c.addNewDialogue("Orfeu: Não tenho tempo para nostalgias marítimas de quem já afundou no esquecimento. Onde encontro uma moeda por aqui?");
        scene2c.addNewDialogue("O marinheiro fecha a cara, ofendido com sua brutalidade implacável");
        scene2c.addNewDialogue("Marinheiro: Vá para o poço do Tártaro, garoto prepotente. Que os espíritos o devorem!");
        scene2c.addNewDialogue("Ele cospe no chão de cinzas e se afasta furioso.");

        // Cena 3 - Fã de Orfeu
        scene3.addNewDialogue("Afastando-se das margens, Orfeu percebe que não está mais sozinho na vasta planície. Uma alma jovem, esguia e pálida, o acompanha a poucos passos de distância, com as mãos entrelaçadas e um sorriso admirado.");
        scene3.addNewDialogue("Fã: Eu não posso acreditar... Esses acordes celestiais, esse rosto abençoado pelas Musas. Você é realmente Orfeu de Trácia! Eu viajava de vilarejo em vilarejo no mundo lá em cima só para ouvir suas incríveis apresentações.");
        scene3.addNewDialogue("Fã: Você iluminou minha vida mortal, grande bardo. Eu escondi isto quando desci aos mundos inferiores porque era tudo o que eu tinha. Quero que você fique com ela, para que sua música nunca pare de tocar.");
        scene3.addNewDialogue("O espírito maravilhado estende a mão translúcida e revela um óbolo perfeitamente conservado, brilhando com um fraco fulgor na penumbra opressora dos Campos de Asfódelo.");
        scene3.addNewDialogue("Como você recebe o presente inesperado do seu antigo admirador?");

        scene3.addNewChoice("Agradecer o carinho com humildade e oferecer uma melodia em retribuição", "s3a_fan", 1, 0, 0, 0, 0);
        scene3.addNewChoice("Aceitar a moeda com melancolia, dizendo que a fama não salvou sua esposa", "s3b_fan", 1, 0, 0, 0, 0);
        scene3.addNewChoice("Pegar a moeda de forma ríspida, dizendo que elogios não têm valor no mundo dos mortos", "s3c_fan", "Charon", 1, -1, 0, 0, 0);

        // Cenas 3a, 3b e 3c - Resultados das escolhas com o Fã
        scene3a.addNewDialogue("Orfeu: Sua gentileza aquece este reino de gelo eterno. Deixe-me retribuir da única forma que sei.");
        scene3a.addNewDialogue("Você toca a canção favorita dele suavemente. A alma chora de alegria pura antes de sumir na névoa, deixando a moeda em suas mãos e o seu coração levemente reconfortado.");

        scene3b.addNewDialogue("Orfeu: Minha fama não impediu a víbora de picar o calcanhar da minha amada... Mas essa sua moeda me ajudará a tentar consertar minha falha.");
        scene3b.addNewDialogue("Você aceita o óbolo de forma solene. O fã abaixa a cabeça com profunda empatia, compreendendo sua dor, e respeitosamente deixa você sozinho.");

        scene3c.addNewDialogue("Orfeu: Elogios de fantasmas não valem nada neste inferno. Mas o seu dinheiro será muito útil para mim.");
        scene3c.addNewDialogue("Você arranca a moeda da mão da alma. O jovem recua assustado e profundamente magoado com a frieza do ídolo que tanto admirava em vida, fugindo para longe de você.");

        // Cena 4 - Ataque de Keres
        scene4.addNewDialogue("O bardo segue viagem por um campo desolado até que um guincho estridente e demoníaco corta o silêncio mórbido. Do céu coberto de trevas, criaturas aladas e grotescas com presas afiadas mergulham em direção a Orfeu. São as terríveis Keres, espíritos da matança.");
        scene4.addNewDialogue("Elas não parecem interessadas na carne de Orfeu, mas sim no brilho dourado e divino de sua harpa. As garras enferrujadas rasgam o ar, ameaçando destruir o intruso para arrancar o instrumento sagrado de suas mãos.");
        scene4.addNewDialogue("Orfeu recua rapidamente, desviando de uma investida rasante que por pouco não rasga seu ombro. Ele sabe que armas comuns seriam inúteis. Suas mãos se posicionam instintivamente sobre as cordas mágicas de Apolo.");
        scene4.addNewDialogue("Qual música você usará para repelir as Keres famintas?");

        scene4.addNewChoice("Música sobre esperança de um futuro melhor", "s4a_keres", 0, 0, 0, 0, 0);
        scene4.addNewChoice("Música sobre a fatalidade da morte", "s4b_keres", 0, 0, 0, 0, 0);
        scene4.addNewChoice("Música sobre heróis matando monstros", "s4c_keres", 1, 0, 0, 0, 0);

        // Cenas 4a, 4b e 4c - Resultados das escolhas com as Keres
        scene4a.addNewDialogue("Notas puras e radiantes criam uma redoma invisível de luz pulsante ao seu redor. Acostumadas com a desgraça e o caos, as Keres não suportam a beleza e a pureza extrema do som, fugindo atordoadas para as sombras de onde vieram.");

        scene4b.addNewDialogue("A canção ecoa um lamento tão macabro e definitivo que as próprias Keres perdem a vontade de destruir. Satisfeitas com a energia letárgica que você emanou, elas se dispersam lentamente pelos ares lúgubres sem te ferir.");

        scene4c.addNewDialogue("Acordes violentos e trovejantes simulam o choque de espadas de Héracles e a fúria de Aquiles. As Keres gritam apavoradas com a agressividade sonora e fogem. Na pressa, uma delas derruba um óbolo enferrujado que havia roubado de um cadáver na superfície.");

        // Cena 5 - Caronte (Retorno)
        scene5.addNewDialogue("Com a moeda no bolso e a determinação renovada, Orfeu faz o perigoso caminho de volta até as margens escuras. Caronte está lá novamente, encostado em seu longo remo rústico, aguardando impaciente.");
        scene5.addNewDialogue("Caronte: Você ainda não virou pó e os monstros não devoraram sua carne. Um feito surpreendente para um tocador de harpa. E vejo no brilho dos seus olhos que encontrou o pagamento da travessia.");
        scene5.addNewDialogue("O barqueiro estende a mão esquelética. Orfeu deposita a moeda arduamente conquistada (-1 moeda), garantindo sua passagem para as águas mais traiçoeiras e profundas da morada de Hades.");
        scene5.addNewDialogue("Caronte: Já que pagou, suba no barco de uma vez. Mas me diga, intruso vivo... Caminhar por entre as flores mortas de Asfódelo muda qualquer um. O que achou do destino comum da imensa maioria da raça humana?");
        scene5.addNewDialogue("O que Orfeu expressa sobre sua experiência nos Campos de Asfódelo?");

        scene5.addNewChoice("Expressar compaixão pelas almas", "s5a_charon", "Charon", -1, 1, 0, 0, 0);
        scene5.addNewChoice("Demonstrar desprezo pelas almas e pelo lugar", "s5b_charon", "Charon", -1, -1, 0, 0, 0);

        // Cenas 5a e 5b - Diálogos finais com Caronte
        scene5a.addNewDialogue("Orfeu: Ninguém merece ser esquecido dessa forma, apagado como uma chama sem vento. É uma existência cruel, privada de dor, mas roubada de qualquer alegria. Sinto pena de cada uma daquelas almas.");
        scene5a.addNewDialogue("Caronte acena levemente com a cabeça, seus olhos esverdeados brilhando com uma fagulha de respeito pela sua observação sábia e sua empatia pelas vítimas do destino.");

        scene5b.addNewDialogue("Orfeu: É um bando de inúteis choramingando num mar de poeira e tédio. Aquele lugar me dá nojo, um desperdício imenso de espaço para pessoas que não tiveram coragem de viver e amar de verdade.");
        scene5b.addNewDialogue("Caronte resmunga e aperta o remo com nojo. \"A arrogância de quem ainda tem sangue nas veias é uma doença patética\", diz ele com frieza, virando o rosto ossudo para a escuridão do rio.");

        // Cena 6 - Fim do Capítulo
        scene6.addNewDialogue("Caronte empurra a barca, cortando as águas densas do Estige rumo à próxima região do Submundo.");

        chapter.newScene(scene1);
        chapter.newScene(scene2);
        chapter.newScene(scene2a);
        chapter.newScene(scene2b);
        chapter.newScene(scene2c);
        chapter.newScene(scene3);
        chapter.newScene(scene3a);
        chapter.newScene(scene3b);
        chapter.newScene(scene3c);
        chapter.newScene(scene4);
        chapter.newScene(scene4a);
        chapter.newScene(scene4b);
        chapter.newScene(scene4c);
        chapter.newScene(scene5);
        chapter.newScene(scene5a);
        chapter.newScene(scene5b);
        chapter.newScene(scene6);

        return chapter;
    }

    public static Chapter BuildChapter3(){
        Chapter chapter = new Chapter("Capítulo 3 - Campos Elíseos");

        Scene scene1 = new Scene("Preço da Memória");
        Scene scene1a = new Scene("s1a_memory", "Ilusão da Glória");
        Scene scene1b = new Scene("s1b_memory", "Ilusão da Glória");
        Scene scene1c = new Scene("s1c_memory", "Ilusão da Glória");
        Scene scene2 = new Scene("Ilusão da Glória");
        Scene scene2a = new Scene("s2a_glory", "Encontro com Jasão");
        Scene scene2b = new Scene("s2b_glory", "Encontro com Jasão");
        Scene scene2c = new Scene("s2c_glory", "Encontro com Jasão");
        Scene scene3 = new Scene("Encontro com Jasão");
        Scene scene3a = new Scene("s3a_jason", "s4_charon");
        Scene scene3b = new Scene("s3b_jason", "s4_charon");
        Scene scene3c = new Scene("s3c_jason", "s4_charon");
        Scene scene4 = new Scene("s4_charon", null);

        // Cena 1 - O Preço da Memória
        scene1.addNewDialogue("Orfeu adentra os Campos Elíseos, um local vibrante banhado por grama dourada e brisa suave. Perto da margem do rio Lete, cujas águas apagam as memórias dos mortos, ele nota uma alma segurando um óbolo.");
        scene1.addNewDialogue("Alma Nobre: Músico vivo... estou exausto. As lembranças das minhas perdas no mundo me torturam diariamente, mas tenho pavor do vazio total. Guardei este óbolo, mas agora não vejo sentido na viagem.");
        scene1.addNewDialogue("Alma Nobre: Se você puder me dar uma razão sólida para não apagar minha própria essência nas águas deste rio, esta moeda é sua. Caso contrário, beberei o esquecimento e afundarei esta prata no lodo.");
        scene1.addNewDialogue("Qual argumento Orfeu usará para salvar as memórias do nobre e conseguir a moeda?");

        scene1.addNewChoice("Nossas memórias são os alicerces de quem amamos", "s1a_memory", 1, 0, 0, 0, 0);
        scene1.addNewChoice("A dor prova que um dia nós estivemos vivos", "s1b_memory", 1, 0, 0, 0, 0);
        scene1.addNewChoice("Sua covardia me dá nojo", "s1c_memory", "Charon", 1, -1, 0, 0, 1);

        // Cenas 1a, 1b e 1c - Resultados das escolhas com a Alma Nobre
        scene1a.addNewDialogue("Orfeu: Apagar sua dor é apagar também os sorrisos e a luz de quem você amou.");
        scene1a.addNewDialogue("A alma recua, emocionada com a verdade de suas palavras, e lhe entrega a prata cintilante com profunda gratidão.");

        scene1b.addNewDialogue("Orfeu: O vazio infinito é pior que a angústia da perda. Aceite o peso da sua história.");
        scene1b.addNewDialogue("Compreendendo a melancolia da afirmação, o nobre chora em silêncio e passa a moeda para suas mãos quentes.");

        scene1c.addNewDialogue("Orfeu: Você chora por dores patéticas enquanto eu desbravo o submundo pelo que é meu. Você não é digno dos Campos Elíseos, me entregue o óbolo antes que eu mesmo o jogue nesse rio maldito.");
        scene1c.addNewDialogue("Amedrontado pela crueldade egoísta e agressiva do bardo, o nobre encolhe-se e simplesmente solta a moeda em suas mãos.");

        // Cena 2 - A Ilusão da Glória
        scene2.addNewDialogue("Com a moeda guardada, o bardo se afasta do rio. Ao longe, um banquete divino atrai heróis e semideuses. Porém, isolado dessa multidão festiva, um guerreiro imponente afia uma espada de bronze rachada.");
        scene2.addNewDialogue("Guerreiro: Você cheira a suor e terra molhada, mortal. Veio buscar histórias épicas de vitórias para cantar no mundo de cima? Saiba que a glória eterna aqui não passa de vento vazio em nossa mente.");
        scene2.addNewDialogue("Guerreiro: Eu daria toda a minha fama conquistada nas areias de Troia apenas para sentir o calor do sol na pele mais uma vez, ou para abraçar minha filha. A morte revela a grande farsa da guerra.");
        scene2.addNewDialogue("Como Orfeu responde à revelação amarga do velho soldado?");

        scene2.addNewChoice("O paraíso é justo, pois o amor deles pelo mundo lhes custou tudo", "s2a_glory", 0, 0, 1, 0, 0);
        scene2.addNewChoice("A eternidade dourada não apaga a dor do que foi perdido", "s2b_glory", 0, 1, 0, 1, 0);
        scene2.addNewChoice("Sua glória inútil não me interessa, seu fantasma patético", "s2c_glory", "Charon", 0, -1, 0, 0, 1);

        // Cenas 2a, 2b e 2c - Resultados das escolhas com o Guerreiro
        scene2a.addNewDialogue("Orfeu: Eles sacrificaram seu futuro para proteger suas famílias e cidades. A paz é a única recompensa justa.");
        scene2a.addNewDialogue("O soldado suspira e sorri com amargura, encontrando um breve e doce conforto ao lembrar que lutou por quem amava.");

        scene2b.addNewDialogue("Orfeu: Vi reis chorarem na luz. O paraíso é apenas uma jaula bonita; a angústia por tudo o que não puderam viver os assombra para sempre.");
        scene2b.addNewDialogue("O guerreiro concorda solenemente.");

        scene2c.addNewDialogue("Orfeu: Você viveu apenas para derramar sangue e agora chora como um covarde por ter perdido o sol. Guarde sua autopiedade para si, pois sua existência vazia me dá nojo.");
        scene2c.addNewDialogue("O guerreiro trinca os dentes e aperta o cabo da espada, ofendido com o seu desprezo e crueldade, e vira as costas abruptamente.");

        // Cena 3 - Encontro com Jasão
        scene3.addNewDialogue("A luz reconfortante dos Elíseos ilumina uma grande oliveira. Sob ela, descansando em uma túnica simples, está Jasão, o líder dos Argonautas. Ao notar o bardo, ele levanta-se surpreso com o calor humano.");
        scene3.addNewDialogue("Jasão: Orfeu? Pelos deuses celestes, a vida ainda corre quente em suas veias! Que ventos tempestuosos e trágicos trouxeram o maior músico de todos os tempos e meu velho amigo até o fim da linha?");
        scene3.addNewDialogue("Orfeu: Vim buscar minha esposa, Jasão. Não posso aceitar que o fio da vida de Eurídice tenha sido cortado pelas Moiras de forma tão prematura. Preciso resgatá-la do fundo deste abismo sombrio.");
        scene3.addNewDialogue("Jasão: O amor... Força mais destrutiva que monstros marinhos. Confiei em Médea e ela assassinou nossos filhos para me punir. Conquistei o Velocino, mas uma mulher destroçou minha alma. Vale a pena?");
        scene3.addNewDialogue("Qual será a resposta de Orfeu ao questionamento fatalista de Jasão?");

        scene3.addNewChoice("O amor verdadeiro cura, não destrói", "s3a_jason", 0, 0, 1, 0, 0);
        scene3.addNewChoice("A dor da perda é o nosso destino comum", "s3b_jason", 0, 0, 0, 1, 0);
        scene3.addNewChoice("O passado de vocês não dita o meu futuro", "s3c_jason", 0, 0, 0, 0, 1);

        // Cenas 3a, 3b e 3c - Resultados das escolhas com Jasão
        scene3a.addNewDialogue("Orfeu: Médea foi consumida pela obsessão pura. O que sinto por Eurídice é divino e lutarei por isso até o fim.");
        scene3a.addNewDialogue("Jasão suspira, admirando sua convicção inabalável e desejando-lhe muita sorte.");

        scene3b.addNewDialogue("Orfeu: Nossas histórias sempre terminam em desgraça, meu capitão. Somos peões na mão dos deuses.");
        scene3b.addNewDialogue("O herói abaixa a cabeça com peso, compartilhando do seu luto profundo em absoluto silêncio.");

        scene3c.addNewDialogue("Orfeu mantém uma postura pragmática e firme, recusando-se a comparar seu casamento de luz com os erros letais de Jasão. O herói balança a cabeça, reconhece a teimosia de Orfeu e deixa o caminho livre.");

        // Cena 4 - O Pedágio de Caronte
        scene4.addNewDialogue("Os campos férteis acabam abruptamente, dando lugar às margens escuras do próximo afluente do rio infernal. Lá, a velha barca de madeira balança na água negra. Caronte o aguarda com a mão ossuda estendida.");
        scene4.addNewDialogue("Orfeu deposita o óbolo de prata que conquistou na mão fria de Caronte (-1 moeda). O barqueiro avalia o brilho do metal com um leve grunhido de aprovação e abre espaço para o bardo subir em sua barca.");
        scene4.addNewDialogue("A madeira range ameaçadoramente sob os pés do bardo enquanto a barca rasga a correnteza cada vez mais violenta do rio. Deixando as memórias douradas dos Elíseos para trás, o frio absoluto do núcleo do Submundo perfura os ossos de Orfeu, um alerta sombrio de que o próximo destino exigirá mais do que apenas moedas e belas canções.");

        chapter.newScene(scene1);
        chapter.newScene(scene1a);
        chapter.newScene(scene1b);
        chapter.newScene(scene1c);
        chapter.newScene(scene2);
        chapter.newScene(scene2a);
        chapter.newScene(scene2b);
        chapter.newScene(scene2c);
        chapter.newScene(scene3);
        chapter.newScene(scene3a);
        chapter.newScene(scene3b);
        chapter.newScene(scene3c);
        chapter.newScene(scene4);

        return chapter;
    }

    public static Chapter BuildChapter4(){
        Chapter chapter = new Chapter("Capítulo 4 - O Tártaro");

        Scene scene1 = new Scene("Encontro com Sísifo");
        Scene scene1a = new Scene("s1a_sisyphus", "Conversa no Abismo");
        Scene scene1b = new Scene("s1b_sisyphus", "Conversa no Abismo");
        Scene scene1c = new Scene("s1c_sisyphus", "Conversa no Abismo");
        Scene scene2 = new Scene("Conversa no Abismo");
        Scene scene2a = new Scene("s2a_abyss", "Portas de Hades");
        Scene scene2b = new Scene("s2b_abyss", "Portas de Hades");
        Scene scene2c = new Scene("s2c_abyss", "Portas de Hades");
        Scene scene3 = new Scene("Portas de Hades");
        Scene scene3a = new Scene("s3a_gates", null);
        Scene scene3b = new Scene("s3b_gates", null);
        Scene scene3c = new Scene("s3c_gates", null);

        // Cena 1 - Encontro com Sísifo
        scene1.addNewDialogue("Orfeu desce pelas encostas quentes e sufocantes do Tártaro. Em meio à escuridão, ele avista um homem suado empurrando uma rocha colossal ladeira acima. A pedra escorrega e rola de volta para a base.");
        scene1.addNewDialogue("O homem simplesmente enxuga a testa e abre um sorriso caloroso ao notar o bardo.");
        scene1.addNewDialogue("Sísifo: Ora, um vivo no Tártaro! Faz eras que não vejo sangue quente por essas bandas. As almas recém-chegadas não param de sussurrar sobre suas melodias no escuro. Você deve ser o famoso Orfeu.");
        scene1.addNewDialogue("Orfeu: E você é Sísifo, o antigo rei de Corinto. Não achei que ainda tivesse fôlego para sorrir com essa rocha pesando sobre os seus ombros pela eternidade.");
        scene1.addNewDialogue("Sísifo: A eternidade é longa demais para não se fazer pausas, meu jovem! Deixe a pedra aí por um momento. O palácio de Hades não está longe. Gostaria de acompanhá-lo e conversar um pouco no caminho.");
        scene1.addNewDialogue("Como Orfeu reage à oferta calorosa e repentina do antigo rei?");

        scene1.addNewChoice("Aceito sua companhia, a caminhada tem sido muito solitária", "s1a_sisyphus", "Sisyphus", 0, 1, 0, 0, 0);
        scene1.addNewChoice("Não vejo sentido, mas aceito alguém para dividir a escuridão", "s1b_sisyphus", "Sisyphus", 0, 1, 0, 0, 0);
        scene1.addNewChoice("Conheço sua fama de trapaceiro. Mantenha distância se vier comigo", "s1c_sisyphus", 0, 0, 0, 0, 0);

        // Cenas 1a, 1b e 1c - Resultados das escolhas com Sísifo
        scene1a.addNewDialogue("Orfeu: Se deseja caminhar ao meu lado, serei grato pela presença.");
        scene1a.addNewDialogue("Sísifo abre um sorriso acolhedor, assentindo positivamente com a cabeça e demonstrando profunda satisfação com a aceitação.");

        scene1b.addNewDialogue("Orfeu: Nossas histórias são de dor, Sísifo. Mas não recuso alguém para compartilhar o silêncio.");
        scene1b.addNewDialogue("O rei suspira e concorda com um olhar solene, colocando-se respeitosamente ao seu lado.");

        scene1c.addNewDialogue("Orfeu: O caminho é livre, mas não confio em você.");
        scene1c.addNewDialogue("Sísifo não perde o sorriso amigável; ele apenas faz uma pequena mesura e garante em tom muito educado que manterá uma distância respeitosa.");

        // Cena 2 - Uma Conversa no Abismo
        scene2.addNewDialogue("Enquanto caminham pelos desfiladeiros de basalto, o calor opressor do Tártaro parece não incomodar Sísifo. Ele observa Orfeu com uma curiosidade genuína, tentando desvendar o coração do bardo.");
        scene2.addNewDialogue("Sísifo: Diga-me, Orfeu... O que realmente impulsiona um homem a descer vivo ao abismo? Conheci o desespero e a ambição em vida, mas desafiar o senhor dos mortos exige algo que foge à razão.");
        scene2.addNewDialogue("Sísifo: Quando você olha para as portas daquele palácio, o que é que grita mais alto dentro do seu peito? É apenas a saudade da sua esposa, ou há algo mais pesado guiando os seus passos?");
        scene2.addNewDialogue("O que Orfeu revela sobre seus sentimentos e como lida com o questionamento?");

        scene2.addNewChoice("O amor dela é a única luz que me mantém respirando", "s2a_abyss", "Sisyphus", 0, 1, 0, 0, 0);
        scene2.addNewChoice("A dor do luto é uma âncora que me arrastou para cá", "s2b_abyss", "Sisyphus", 0, 1, 0, 0, 0);
        scene2.addNewChoice("Por que tanto interesse nos meus motivos, mestre das mentiras?", "s2c_abyss", 0, 0, 0, 0, 0);

        // Cenas 2a, 2b e 2c - Resultados das conversas no abismo
        scene2a.addNewDialogue("Orfeu: Sem Eurídice, meu coração já está morto. Suportaria mil infernos por ela.");
        scene2a.addNewDialogue("Sísifo o observa com uma empatia silenciosa, parecendo admirar a devoção pura e inabalável do bardo.");

        scene2b.addNewDialogue("Orfeu: O vazio que ela deixou não tem cura. Não há futuro no mundo lá em cima para mim.");
        scene2b.addNewDialogue("Sísifo concorda devagar, parecendo compreender perfeitamente o peso da melancolia que o consome.");

        scene2c.addNewDialogue("Orfeu: Não vou entregar meus sentimentos para você usar contra mim.");
        scene2c.addNewDialogue("Sísifo levanta as mãos calmamente, respondendo com extrema gentileza que apenas buscava uma boa história para aliviar o tédio.");

        // Cena 3 - As Portas de Hades
        scene3.addNewDialogue("A névoa se dissipa, revelando os imensos portões de ébano e bronze do palácio de Hades. O som de chicotes estalando ao longe ecoa pelas pedras. As Fúrias estão fazendo a ronda.");
        scene3.addNewDialogue("Sísifo: Bem, minha pequena caminhada termina aqui. Mais um passo e os guardas do Tártaro me farão rolar a pedra com o dobro do peso. Foi uma excelente conversa, bardo.");
        scene3.addNewDialogue("Sísifo: Entre lá e faça os senhores do Submundo chorarem com a sua harpa. Espero, do fundo do meu coração, que você consiga o que veio buscar.");
        scene3.addNewDialogue("O que Orfeu faz antes de Sísifo voltar ao castigo?");

        scene3.addNewChoice("Expressar profunda gratidão pelas palavras e pela companhia sincera", "s3a_gates", "Sisyphus", 0, 1, 0, 0, 0);
        scene3.addNewChoice("Desejar que a eternidade seja misericordiosa com ele", "s3b_gates", "Sisyphus", 0, 1, 0, 0, 0);
        scene3.addNewChoice("Dizer para ele sumir logo antes que tente alguma armadilha", "s3c_gates", 0, 0, 0, 0, 0);

        // Cenas 3a, 3b e 3c - Despedida de Sísifo
        scene3a.addNewDialogue("Orfeu: Sua presença tornou a escuridão menos opressora, Sísifo. Agradeço de coração por ter caminhado ao meu lado até aqui.");
        scene3a.addNewDialogue("O antigo rei sorri, recebendo as palavras amáveis com um aceno nobre antes de se afastar para o seu fardo.");

        scene3b.addNewDialogue("Orfeu: Que as pedras um dia rolem a seu favor, Sísifo.");
        scene3b.addNewDialogue("O homem sorri com a sua empatia, absorvendo as palavras amargas e gentis enquanto caminha de volta para a escuridão do Tártaro.");

        scene3c.addNewDialogue("Orfeu: Volte para a sua pedra antes que eu perceba qual era o seu verdadeiro truque.");
        scene3c.addNewDialogue("Sísifo dá uma leve risada elegante, desejando-lhe boa sorte com imensa cortesia antes de virar as costas.");

        chapter.newScene(scene1);
        chapter.newScene(scene1a);
        chapter.newScene(scene1b);
        chapter.newScene(scene1c);
        chapter.newScene(scene2);
        chapter.newScene(scene2a);
        chapter.newScene(scene2b);
        chapter.newScene(scene2c);
        chapter.newScene(scene3);
        chapter.newScene(scene3a);
        chapter.newScene(scene3b);
        chapter.newScene(scene3c);

        return chapter;
    }

    public static Chapter BuildChapter5(){
        Chapter chapter = new Chapter("Capítulo 5 - A Casa de Hades");

        Scene scene1 = new Scene("Sala do Trono");
        Scene scene2 = new Scene("Julgamento de Hades");
        Scene scene2a = new Scene("s2a_judgment", "Encontro com Eurídice");
        Scene scene2b = new Scene("s2b_judgment", null);
        Scene scene3 = new Scene("Encontro com Eurídice");
        Scene scene3a = new Scene("s3a_eurydice", "s4_leaving");
        Scene scene3b = new Scene("s3b_eurydice", "s4_leaving");
        Scene scene3c = new Scene("s3c_eurydice", "s4_leaving");
        Scene scene4 = new Scene("s4_leaving", null);

        // Cena 1 - A Sala do Trono
        scene1.addNewDialogue("Os imensos portões de ébano se abrem com um estrondo pesado. Orfeu adentra um salão colossal cujo chão é cravejado com diamantes brutos, enquanto as imensas colunas de obsidiana são adornadas com veios de ouro puro e rubis do tamanho de maçãs.");
        scene1.addNewDialogue("No fundo, repousa o rei do Submundo. Seus olhos são frios e calculistas. Ao seu lado, a rainha Perséfone exibe uma beleza pálida e compreensiva, coroada com flores tecidas em prata e rubis.");
        scene1.addNewDialogue("Hades: Orfeu de Trácia. Até mesmo os imortais no cume do Olimpo se calam para ouvir os ecos da sua harpa. Reconheço a sua fama, mortal, mas fama não é moeda de troca no meu reino. O que o traz ao fundo da terra?");
        scene1.addNewDialogue("Perséfone: Hades, meu amor, os ventos do Tártaro me contaram a jornada que ele trilhou para chegar até nossa casa. Deixe que ele faça sua súplica.");
        scene1.addNewDialogue("O bardo guarda sua harpa. Ele sabe que a música não será suficiente para convencer os senhores do Submundo; ele precisará usar o peso de suas próprias palavras.");
        scene1.addNewDialogue("Qual é o principal argumento de Orfeu para que os deuses devolvam Eurídice?");

        scene1.addNewChoice("Nossas almas foram forjadas como uma só, separá-las é um erro", "Julgamento de Hades", 0, 0, 0, 0, 0);
        scene1.addNewChoice("A escuridão já tomou muito do meu mundo lá em cima, não leve a minha única luz", "Julgamento de Hades", 0, 0, 0, 0, 0);
        scene1.addNewChoice("Ela foi roubada por uma fatalidade cruel antes de viver seu destino!", "Julgamento de Hades", 0, 0, 0, 0, 0);

        // Cena 2 - O Julgamento de Hades
        scene2.addNewDialogue("O silêncio que se segue é denso e sufocante, quebrado apenas pelo tilintar das joias no chão. Hades se inclina em seu trono de ouro negro, pesando a alma de Orfeu e avaliando as atitudes do bardo durante sua jornada.");
        scene2.addNewDialogue("Como Orfeu responde perante o tribunal de Hades?");

        scene2.addNewChoice("Engolir o orgulho e pedir perdão por sua amargura", "s2a_judgment", 0, 0, 1, 0, 0);
        scene2.addNewChoice("Dobrar a aposta e exigir Eurídice de qualquer maneira", "s2b_judgment", 0, 0, 0, 0, 1);

        // Cenas 2a e 2b - Resultados do Julgamento
        scene2a.addNewDialogue("Orfeu abaixa a cabeça.");
        scene2a.addNewDialogue("Orfeu: O desespero cegou meu julgamento, grande Hades. Minha dor me transformou em um monstro para os seus súditos, e eu me arrependo.");
        scene2a.addNewDialogue("Perséfone toca o braço do marido. Hades suspira e concorda em lhe dar uma chance.");
        scene2a.addNewDialogue("Hades: Leve sua amada de volta para o sol, mas sob uma única regra inquebrável. Ela caminhará atrás de você. Se você olhar para trás antes de deixarem a totalidade dos meus domínios, o acordo estará quebrado e ela será minha para sempre.");
        scene2a.addNewDialogue("Hades estala os dedos e uma pequena bolsa de couro cai aos pés de Orfeu, contendo dois óbolos para a travessia de volta.");

        scene2b.addNewDialogue("Orfeu: Seus mortos são fracos e o seu reino é patético! Eu exijo que ela volte comigo agora!");
        scene2b.addNewDialogue("Hades ergue a mão, com chamas sombrias nos olhos.");
        scene2b.addNewDialogue("Hades: Tolo insolente. Você se juntará a eles pela eternidade!");
        scene2b.addNewDialogue("Correntes de bronze surgem do chão, prendendo Orfeu para sempre no Tártaro. (Final Prematuro)");

        // Cena 3 - Eurídice
        scene3.addNewDialogue("Perséfone ergue a mão e uma bruma de ametista se forma no centro do salão. Das névoas, uma figura feminina com véu de noiva caminha hesitante. É Eurídice.");
        scene3.addNewDialogue("Ela levanta o olhar e para bruscamente, confusa. Ela olha para as próprias mãos translúcidas e depois para o peito arfante de Orfeu, incapaz de processar o que está acontecendo.");
        scene3.addNewDialogue("Eurídice: Orfeu? É você mesmo? Mas você... você está vivo. Como chegou até aqui? Por quê?");
        scene3.addNewDialogue("Orfeu: Eu vim te buscar, meu amor. Os senhores do Submundo nos deram permissão. Nós vamos voltar para casa.");
        scene3.addNewDialogue("Eurídice: Voltar...? Orfeu, eu sinto tanto frio aqui, mas... a dor da picada sumiu. As lágrimas e as tristezas do mundo mortal não me alcançam mais. Voltar significa estar viva, mas também significa sangrar de novo, sofrer de novo. Eu... eu não sei se quero sentir toda aquela dor novamente.");
        scene3.addNewDialogue("Como Orfeu tenta convencer Eurídice a abandonar o conforto apático da morte?");

        scene3.addNewChoice("Lembre-se do sol tocando nosso rosto e de tudo o que ainda vamos viver", "s3a_eurydice", 0, 0, 1, 0, 0);
        scene3.addNewChoice("Por favor, eu imploro, não me deixe sozinho com esse desespero", "s3b_eurydice", 0, 0, 0, 1, 0);
        scene3.addNewChoice("Se você ficar, levarei sua memória comigo como um farol eterno", "s3c_eurydice", 0, 1, 0, 0, 0);

        // Cenas 3a, 3b e 3c - Respostas de Eurídice
        scene3a.addNewDialogue("Orfeu exibe um sorriso esperançoso, segurando as mãos pálidas dela com carinho.");
        scene3a.addNewDialogue("Orfeu: A vida tem espinhos, mas o nosso amor é maior que a morte. Deixe-me cuidar de você de novo.");
        scene3a.addNewDialogue("Eurídice sorri docemente, recuperando a firmeza para retornar ao seu lado.");

        scene3b.addNewDialogue("Orfeu agarra a própria cabeça, com a voz embargada e lágrimas nos olhos.");
        scene3b.addNewDialogue("Orfeu: O mundo lá em cima é um vazio sem fim sem você. Eu não consigo respirar, eu não consigo viver! Por favor, volte comigo!");
        scene3b.addNewDialogue("Eurídice se comove profundamente com a sua vulnerabilidade extrema e assente, aproximando-se para acalmá-lo.");

        scene3c.addNewDialogue("Orfeu respira fundo, aceitando o livre-arbítrio dela com uma dignidade melancólica e serena.");
        scene3c.addNewDialogue("Orfeu: Se a sua escolha for o descanso, eu honrarei sua paz e levarei sua luz em minhas canções para sempre.");
        scene3c.addNewDialogue("Eurídice o olha com profunda admiração por respeitar sua vontade, tocada pela pureza e maturidade do seu amor.");

        // Cena 4 - Saindo da Casa de Hades
        scene4.addNewDialogue("Afastando-se dos tronos com dois óbolos em mãos, Orfeu guarda a bolsa com extremo cuidado. Ele olha profundamente nos olhos de sua esposa pela última vez.");
        scene4.addNewDialogue("Orfeu: Lembre-se do acordo. Hades afastou os monstros, o caminho estará livre de perigos, mas eu serei proibido de olhar para o seu rosto. Siga o som dos meus passos.");
        scene4.addNewDialogue("Eurídice concorda lentamente com a cabeça, ainda levemente atônita, mas com o coração decidido. Orfeu solta a mão de sua amada, sentindo o calor abandonar seus dedos. Ele vira as costas para Eurídice e encara os imensos portões do palácio, dando o primeiro passo em direção à longa e angustiante caminhada rumo ao mundo dos vivos.");

        chapter.newScene(scene1);
        chapter.newScene(scene2);
        chapter.newScene(scene2a);
        chapter.newScene(scene2b);
        chapter.newScene(scene3);
        chapter.newScene(scene3a);
        chapter.newScene(scene3b);
        chapter.newScene(scene3c);
        chapter.newScene(scene4);

        return chapter;
    }

    public static Chapter BuildChapter6(){
        Chapter chapter = new Chapter("Capítulo 6 - A volta pelo Tártaro");

        Scene scene1 = new Scene("O Eco do Abismo");
        Scene scene1a = new Scene("s1a_abyss_echo", "A Sombra na Trilha");
        Scene scene1b = new Scene("s1b_abyss_echo", "A Sombra na Trilha");
        Scene scene1c = new Scene("s1c_abyss_echo", "A Sombra na Trilha");
        Scene scene2 = new Scene("A Sombra na Trilha");
        Scene scene2a = new Scene("s2a_trail_shadow", "O Atalho Oculto");
        Scene scene2b = new Scene("s2b_trail_shadow", "O Atalho Oculto");
        Scene scene2c = new Scene("s2c_trail_shadow", "O Atalho Oculto");
        Scene scene3 = new Scene("O Atalho Oculto");
        Scene scene3a = new Scene("s3a_hidden_shortcut", null);
        Scene scene3b = new Scene("s3b_hidden_shortcut", null);
        Scene scene3c = new Scene("s3c_hidden_shortcut", null);

        // Cena 1 - O Eco do Abismo
        scene1.addNewDialogue("O silêncio entre o casal na trilha de pedra é denso, cortado apenas pelo som abafado de passos sobre a cinza vulcânica. À margem do caminho, o Tártaro exibe toda a sua crueza: um abismo escaldante onde gritos de desespero e o estalar de chicotes celestes ecoam sem trégua, servindo como um lembrete constante e aterrorizante do que os aguarda caso falhem na travessia.");
        scene1.addNewDialogue("Orfeu caminha à frente, sentindo a tensão pesando em seus ombros. Ele tenta quebrar a atmosfera gélida que se instalou desde que deixaram o palácio.");
        scene1.addNewDialogue("Orfeu: Quando voltarmos para a Trácia, construiremos aquela casa perto do riacho que você tanto queria... O inverno lá fora é rigoroso agora, Eurídice, mas logo a bela primavera vai voltar para aquecer seus dias de novo.");
        scene1.addNewDialogue("Eurídice dá alguns passos em silêncio, a voz soando distante, desprovida da vivacidade de antes.");
        scene1.addNewDialogue("Eurídice: A primavera... É estranho pensar nela. Enquanto eu vagava naquela névoa cinzenta, o tempo perdeu o sentido. Diga-me, Orfeu... O mundo lá em cima continuou gelado e silencioso enquanto eu apodrecia sob a terra? A vida seguiu seu curso tão facilmente assim, como se eu nunca tivesse existido?");
        scene1.addNewDialogue("Como Orfeu responde à apatia e ao distanciamento melancólico de sua esposa?");

        scene1.addNewChoice("O mundo parou para mim, cada segundo sem você foi uma eternidade", "s1a_abyss_echo", 0, 1, 0, 0, 1);
        scene1.addNewChoice("A vida lá fora continua fria e vazia sem o seu reflexo nela", "s1b_abyss_echo", 0, 0, 1, 0, 1);
        scene1.addNewChoice("Não pense no passado, o futuro na superfície é o que importa agora", "s1c_abyss_echo", 0, 0, 0, 0, 0);

        // Cenas 1a, 1b e 1c - Resultados das escolhas
        scene1a.addNewDialogue("Orfeu aperta as mãos, contendo a angústia na voz.");
        scene1a.addNewDialogue("Orfeu: Para os outros a vida continuou, mas para o meu coração o tempo congelou no dia em que te perdi.");
        scene1a.addNewDialogue("Eurídice o olha por breves segundos, sentindo o peso da devoção dele tocar o eco frio que habita nela agora.");

        scene1b.addNewDialogue("Orfeu abaixa a cabeça, caminhando com passos arrastados pela culpa.");
        scene1b.addNewDialogue("Orfeu: Ninguém cantou, nenhuma alegria verdadeira restou na superfície enquanto você jazia aqui.");
        scene1b.addNewDialogue("Ela solta um suspiro frágil, absorvendo a dor compartilhada que os une naquelas sombras.");

        scene1c.addNewDialogue("Orfeu endurece o tom, tentando apressar o passo para abafar os gritos do abismo.");
        scene1c.addNewDialogue("Orfeu: Deixe os mortos com o esquecimento. Nós vencemos a morte, o que importa é o amanhã.");
        scene1c.addNewDialogue("Eurídice desvia o olhar para o abismo fumegante, respondendo apenas com um silêncio apático e desconfortável.");

        // Cena 2 - A Sombra na Trilha
        scene2.addNewDialogue("Enquanto avançam pelas fendas rochosas, a presença de Eurídice parece emitir um frio sutil que contrasta com o calor sufocante do Tártaro. Ela caminha rigidamente alguns passos atrás, absorvendo o ambiente macabro ao seu redor de uma forma perturbadora.");
        scene2.addNewDialogue("Orfeu tenta puxar assunto novamente, buscando qualquer indício da mulher vibrante que ela costumava ser antes da tragédia.");
        scene2.addNewDialogue("Orfeu: Lembra-se de quando tocávamos harpa sob as oliveiras? Você dizia que a música era a única coisa capaz de acalmar os deuses.");
        scene2.addNewDialogue("Eurídice para de andar por um instante. Ao fundo, o grito de uma alma torturada corta o ar, e ela parece sintonizar com o som antes de responder.");
        scene2.addNewDialogue("Eurídice: A música... parece tão distante, Orfeu. Aqui embaixo, aprendi que o silêncio é a única verdade duradoura. As vozes do mundo dos vivos parecem apenas ruídos fúteis de quem ainda não entendeu que vai morrer.");
        scene2.addNewDialogue("Como Orfeu lida com a estranheza e a frieza crescente nas palavras de Eurídice?");

        scene2.addNewChoice("A minha música trará você de volta à vida por completo, eu prometo", "s2a_trail_shadow", 0, 1, 0, 0, 1);
        scene2.addNewChoice("O Submundo deixou marcas profundas em nós dois, vamos superar isso juntos", "s2b_trail_shadow", 0, 0, 1, 0, 1);
        scene2.addNewChoice("Você não é um fantasma, pare de falar como se pertencesse a este lugar maldito", "s2c_trail_shadow", 0, 0, 0, 0, 0);

        // Cenas 2a, 2b e 2c - Resultados das escolhas
        scene2a.addNewDialogue("Orfeu ergue a voz levemente, cheio de esperança fervorosa.");
        scene2a.addNewDialogue("Orfeu: Assim que deixarmos este reino, o calor do sol vai derreter esse gelo em sua alma.");
        scene2a.addNewDialogue("Eurídice esboça um sorriso melancólico e frágil, deixando-se guiar pela fé inabalável do bardo.");

        scene2b.addNewDialogue("Orfeu suspira profundamente, sentindo o peso do sofrimento dela refletido em seu próprio peito.");
        scene2b.addNewDialogue("Orfeu: Nenhum de nós saiu ileso daqui, mas suportaremos o fardo lado a lado.");
        scene2b.addNewDialogue("Ela assente em silêncio, encontrando consolo na dor mútua.");

        scene2c.addNewDialogue("Orfeu endurece a postura, irritado com a resignação dela diante da morte.");
        scene2c.addNewDialogue("Orfeu: Eu cruzei o Submundo para te tirar daqui, então comece a agir como alguém que quer viver!");
        scene2c.addNewDialogue("Eurídice se cala imediatamente, fechando-se em uma apatia ainda mais profunda e distante.");

        // Cena 3 - O Atalho Oculto
        scene3.addNewDialogue("Deixando o calor sufocante e os gritos do Tártaro para trás, Orfeu nota que a trilha rochosa se desvia das rotas comuns dos barqueiros. Conforme as ordens e o favor secreto concedidos por Hades, uma fenda oculta na muralha de basalto se abre diante deles, revelando um túnel iluminado por um brilho dourado e suave.");
        scene3.addNewDialogue("Não há necessidade de enfrentar os rios ou os barcos de Caronte; este atalho místico conduz diretamente aos Campos Elíseos, facilitando a subida final para o mundo dos vivos.");
        scene3.addNewDialogue("Apesar de estarem livres do perigo imediato, a distância emocional entre os dois parece maior do que nunca. Eurídice caminha cabisbaixa, olhando fixamente para os calcanhares de Orfeu na poeira, parecendo mais uma espectra obediente do que uma esposa resgatada.");
        scene3.addNewDialogue("Eurídice: Estamos passando por este caminho dourado... Sinto o ar mudar, mas sinto um vazio estranho. Diga-me a verdade, Orfeu: quando chegarmos à luz, eu serei de fato quem eu era, ou apenas uma lembrança viva que errou o caminho de volta para a cova?");
        scene3.addNewDialogue("O que Orfeu responde à pergunta cortante e angustiante de Eurídice?");

        scene3.addNewChoice("Você será sempre o meu único lar e o meu maior amor", "s3a_hidden_shortcut", 0, 1, 0, 0, 1);
        scene3.addNewChoice("Seremos diferentes, mas vamos sarar as feridas juntos no mundo dos vivos", "s3b_hidden_shortcut", 0, 0, 1, 0, 1);
        scene3.addNewChoice("Basta sairmos daqui para que essas tolices saiam da sua cabeça", "s3c_hidden_shortcut", 0, 0, 0, 0, -1);

        // Cenas 3a, 3b e 3c - Resultados das escolhas
        scene3a.addNewDialogue("Orfeu vira o rosto minimamente para trás, sem contudo quebrar a regra de olhar em seus olhos.");
        scene3a.addNewDialogue("Orfeu: Não importa o que mudou em você, meu coração continua batendo apenas por ti.");
        scene3a.addNewDialogue("Eurídice respira fundo, sentindo o calor das palavras resgatar uma centelha de humanidade em seu peito.");

        scene3b.addNewDialogue("Orfeu fala com a voz pesada de melancolia.");
        scene3b.addNewDialogue("Orfeu: Ninguém volta imune da morte, Eurídice. Mas carregaremos essa cicatriz em paz, juntos.");
        scene3b.addNewDialogue("Ela acena com a cabeça lentamente, aceitando a realidade quebrada de seu retorno.");

        scene3c.addNewDialogue("Orfeu suspira com aspereza, impaciente com as dúvidas dela.");
        scene3c.addNewDialogue("Orfeu: Chega de filosofar sobre fantasmas. Só precisamos continuar andando por este atalho e deixar este inferno para trás.");
        scene3c.addNewDialogue("Eurídice se encolhe com a frieza da resposta, mantendo o passo firme, mas completamente isolada em seus próprios pensamentos sombrios.");

        chapter.newScene(scene1);
        chapter.newScene(scene1a);
        chapter.newScene(scene1b);
        chapter.newScene(scene1c);
        chapter.newScene(scene2);
        chapter.newScene(scene2a);
        chapter.newScene(scene2b);
        chapter.newScene(scene2c);
        chapter.newScene(scene3);
        chapter.newScene(scene3a);
        chapter.newScene(scene3b);
        chapter.newScene(scene3c);

        return chapter;
    }

    public static Chapter BuildChapter7(){
        Chapter chapter = new Chapter("Capítulo 7 - A volta por Elíseos");

        Scene scene1 = new Scene("Brilho dos Elíseos");
        Scene scene1a = new Scene("s1a_elysium_glow", "Tentação do Descanso");
        Scene scene1b = new Scene("s1b_elysium_glow", "Tentação do Descanso");
        Scene scene1c = new Scene("s1c_elysium_glow", "Tentação do Descanso");
        Scene scene2 = new Scene("Tentação do Descanso");
        Scene scene2a = new Scene("s2a_temptation", "Portas de Asfódelo");
        Scene scene2b = new Scene("s2b_temptation", "Portas de Asfódelo");
        Scene scene2c = new Scene("s2c_temptation", "Portas de Asfódelo");
        Scene scene3 = new Scene("Portas de Asfódelo");
        Scene scene3a = new Scene("s3a_asphodel_gates", null);
        Scene scene3b = new Scene("s3b_asphodel_gates", null);
        Scene scene3c = new Scene("s3c_asphodel_gates", null);

        // Cena 1 - O Brilho dos Elíseos
        scene1.addNewDialogue("O calor sufocante e os gritos distantes do Tártaro dão lugar a uma brisa suave, perfumada com o aroma de jasmins e águas cristalinas. O atalho dourado conduz Orfeu e Eurídice diretamente aos Campos Elíseos.");
        scene1.addNewDialogue("Aqui, o cenário é de uma beleza deslumbrante e eterna: prados verdes sob um crepúsculo dourado, onde heróis virtuosos e almas justas caminham em paz, conversando sob a sombra de oliveiras perfeitas. É um contraste brutal com o abismo, oferecendo uma paz profunda e melancólica que parece envolver o espírito como um abraço morno.");
        scene1.addNewDialogue("Orfeu respira fundo, sentindo o alívio momentâneo daquele refúgio celestial, e olha de relance por cima do ombro, lembrando-se estritamente de nunca fitar o rosto de sua amada.");
        scene1.addNewDialogue("Orfeu: Veja, Eurídice... Chegamos aos Elíseos. O pior já passou. Sinta o perfume destas flores. Estamos cada vez mais perto da superfície, do sol e da nossa verdadeira casa.");
        scene1.addNewDialogue("Eurídice caminha devagar, seus pés translúcidos tocando a grama macia. Ela ergue o rosto para a luz dourada, absorvendo a serenidade absoluta daquele lugar sagrado, parecendo cada vez mais distante do bardo.");
        scene1.addNewDialogue("Eurídice: É tão silencioso aqui... A dor da picada e o frio do esquecimento desapareceram por completo. Ninguém chora nos Elíseos, Orfeu. Ninguém sofre.");
        scene1.addNewDialogue("Como Orfeu reage à primeira impressão pacífica que os Elíseos exercem sobre a alma de sua esposa?");

        scene1.addNewChoice("A verdadeira vida tem lágrimas e sorrisos, a paz daqui é apenas uma prisão", "s1a_elysium_glow", 0, 1, 0, 0, 0);
        scene1.addNewChoice("Eu entendo o seu cansaço, mas o nosso lugar ainda não é entre os mortos", "s1b_elysium_glow", 0, 1, 1, 0, 0);
        scene1.addNewChoice("Não ouse se render agora, depois de tudo o que atravessei para te buscar", "s1c_elysium_glow", 0, 0, 0, 0, 0);

        // Cenas 1a, 1b e 1c - Resultados das escolhas
        scene1a.addNewDialogue("Orfeu aperta as mãos com firmeza.");
        scene1a.addNewDialogue("Orfeu: A paz eterna sem o calor do toque humano ou da nossa história não passa de uma mentira dourada. Venha comigo, meu amor.");
        scene1a.addNewDialogue("Eurídice o escuta em silêncio, sentindo o chamado do mundo real agitar o marasmo de sua alma.");

        scene1b.addNewDialogue("Orfeu fala com a voz pesada e melancólica, caminhando com passos cautelosos.");
        scene1b.addNewDialogue("Orfeu: O descanso é tentador, mas o nosso destino foi brutalmente interrompido. Precisamos terminar o que começamos.");
        scene1b.addNewDialogue("Ela suspira fundo, reconhecendo a dor que ainda os une.");

        scene1c.addNewDialogue("Orfeu endurece o tom, perdendo a paciência com a hesitação dela.");
        scene1c.addNewDialogue("Orfeu: Eu não enfrentei os deuses e os monstros do inferno para ver você contemplando a paisagem! Continue andando!");
        scene1c.addNewDialogue("Eurídice se encolhe com a hostilidade, distanciando-se ainda mais emocionalmente dele.");

        // Cena 2 - A Tentação do Descanso
        scene2.addNewDialogue("Conforme avançam pelas alamedas iluminadas dos Elíseos, algumas figuras ilustres e almas de antigos poetas e ninfas que habitam o paraíso se aproximam respeitosamente. Ao reconhecerem Eurídice, elas sorriem com compaixão e sussurram convites doces, tentando persuadi-la a abandonar a jornada.");
        scene2.addNewDialogue("Alma dos Elíseos: Doce ninfa... Por que retornar a um mundo mortal onde a velhice consome a carne, a dor dilacera o peito e a morte espreita em cada esquina? Aqui não há invernos rigorosos, nem pranto. Fique conosco na luz eterna.");
        scene2.addNewDialogue("Eurídice para de caminhar. A oferta de uma existência livre de sofrimento atinge o seu espírito desgastado, fazendo-a olhar fixamente para a grama dourada, balançando entre a luz e o compromisso com o bardo.");
        scene2.addNewDialogue("Eurídice: Eles têm razão, Orfeu... Lá em cima, eu era apenas uma mortal frágil destinada ao sofrimento. Aqui, encontrei a paz que o mundo me negou. Diga-me a verdade, olhando no fundo da sua alma... Você me trouxe de volta por amor ao que éramos, ou por saudade egoísta do meu canto e da minha companhia?");
        scene2.addNewDialogue("O que Orfeu responde à pergunta cortante e existencial de Eurídice?");

        scene2.addNewChoice("Eu te amo além da arte, você é a própria razão de o meu coração bater", "s2a_temptation", 0, 1, 1, 0, 0);
        scene2.addNewChoice("A saudade da sua alma rasga o meu peito, eu estou perdido sem o seu reflexo", "s2b_temptation", 0, 1, 0, 1, 0);
        scene2.addNewChoice("Você é minha esposa e minha responsabilidade, pare de fazer perguntas tolas", "s2c_temptation", 0, -5, 0, 0, 1);

        // Cenas 2a, 2b e 2c - Resultados das escolhas
        scene2a.addNewDialogue("Orfeu vira o rosto minimamente, com a voz embargada e transbordando de paixão genuína.");
        scene2a.addNewDialogue("Orfeu: A minha música não vale nada sem você para ouvi-la. Eu te amo, Eurídice, inteiramente e acima de qualquer verso.");
        scene2a.addNewDialogue("Ela sente uma lágrima fantasma escorrer, tocada no fundo do ser pela sinceridade absoluta do bardo.");

        scene2b.addNewDialogue("Orfeu fala com um tom doloroso, carregado de uma melancolia devastadora.");
        scene2b.addNewDialogue("Orfeu: O meu mundo é um deserto cinzento sem a sua presença. Eu não vim buscar a minha musa, vim buscar a minha metade.");
        scene2b.addNewDialogue("Eurídice assente lentamente, compreendendo que a dor da perda dele espelhava a sua própria desolação.");

        scene2c.addNewDialogue("Orfeu endurece a mandíbula, irritado com a dúvida dela em pleno paraíso.");
        scene2c.addNewDialogue("Orfeu: Chega de questionamentos! Você vai voltar para casa porque eu decidi que vai. Agora venha!");
        scene2c.addNewDialogue("Eurídice fecha os olhos, engolindo a mágoa e seguindo seus passos com o coração completamente frio e distante.");

        // Cena 3 - As Portas de Asfódelo
        scene3.addNewDialogue("Avançando pelos limites dos Campos Elíseos, o brilho dourado e suave do paraíso começa a se dissipar, dando lugar a uma fenda rochosa estreita e descendente que se aprofunda na penumbra. É a entrada para o túnel secreto que atravessa os extensos Campos de Asfódelo, conduzindo lentamente à superfície da Terra por uma rota longa e cinzenta.");
        scene3.addNewDialogue("O ar quente e perfumado dos Elíseos vai dando lugar a uma brisa monótona e empoeirada. O silêncio do limbo recai sobre eles, e a saída para o mundo dos vivos ainda está distante exigindo que o casal caminhe por todo o árduo trajeto subterrâneo antes de alcançar a luz definitiva.");
        scene3.addNewDialogue("Eurídice para por um instante na entrada do túnel escuro, sentindo o peso daquela planície esquecida ecoar em sua alma translúcida mais uma vez.");
        scene3.addNewDialogue("Eurídice: Vamos caminhar por este longo corredor de cinzas... Sinto o vazio de Asfódelo nos chamando. O medo de voltar a sofrer ainda me consome, Orfeu. Como ter certeza de que este sacrifício todo não será em vão?");
        scene3.addNewDialogue("O que Orfeu faz para guiar sua esposa pelo túnel e dar início a esta etapa final da jornada?");

        scene3.addNewChoice("A dor faz parte da vida, mas o nosso amor tornará tudo suportável", "s3a_asphodel_gates", 0, 0, 0, 0, 0);
        scene3.addNewChoice("Caminharemos sob o peso desse luto juntos, até que o tempo cure nossas feridas", "s3b_asphodel_gates", 0, 1, 1, 0, 0);
        scene3.addNewChoice("Não ouse hesitar agora que o caminho está aberto para nós!", "s3c_asphodel_gates", 0, -5, 0, 0, 0);

        // Cenas 3a, 3b e 3c - Resultados das escolhas
        scene3a.addNewDialogue("Orfeu estende a mão para trás, sem tocá-la, mantendo o calor da sua presença firme.");
        scene3a.addNewDialogue("Orfeu: Confie em mim. Eu estarei lá para segurar sua mão em cada passo desta travessia.");
        scene3a.addNewDialogue("Eurídice respira fundo, aceitando o risco de viver novamente e dando o passo decisivo para o túnel.");

        scene3b.addNewDialogue("Orfeu abaixa a cabeça, compartilhando do mesmo temor silencioso.");
        scene3b.addNewDialogue("Orfeu: Ninguém prometeu que seria fácil, Eurídice. Mas suportaremos a escuridão de mãos dadas.");
        scene3b.addNewDialogue("Ela assente de forma solene, reunindo forças para cruzar o limiar de Asfódelo.");

        scene3c.addNewDialogue("Orfeu sibila com impaciência, apontando para a escuridão do túnel com raiva contida.");
        scene3c.addNewDialogue("Orfeu: Depois de tudo, você vai amarelar bem na linha de chegada? Ande logo!");
        scene3c.addNewDialogue("Eurídice morde os lábios, trancando qualquer réplica, e avança em direção ao caminho cinzento com o orgulho ferido e o espírito entorpecido.");

        chapter.newScene(scene1);
        chapter.newScene(scene1a);
        chapter.newScene(scene1b);
        chapter.newScene(scene1c);
        chapter.newScene(scene2);
        chapter.newScene(scene2a);
        chapter.newScene(scene2b);
        chapter.newScene(scene2c);
        chapter.newScene(scene3);
        chapter.newScene(scene3a);
        chapter.newScene(scene3b);
        chapter.newScene(scene3c);

        return chapter;
    }

    public static Chapter BuildChapter8(){
        Chapter chapter = new Chapter("Capítulo 8 - A volta por Asfódelo");

        Scene scene1 = new Scene("Névoa do Esquecimento");
        Scene scene1a = new Scene("s1a_asphodel_mist", "Ecos da Distância");
        Scene scene1b = new Scene("s1b_asphodel_mist", "Ecos da Distância");
        Scene scene1c = new Scene("s1c_asphodel_mist", "Ecos da Distância");
        Scene scene2 = new Scene("Ecos da Distância");
        Scene scene2a = new Scene("s2a_distant_echoes", "s3_styx_shore");
        Scene scene2b = new Scene("s2b_distant_echoes", "s3_styx_shore");
        Scene scene2c = new Scene("s2c_distant_echoes", "s3_styx_shore");
        Scene scene3 = new Scene("s3_styx_shore", null);

        // Cena 1 - A Névoa do Esquecimento
        scene1.addNewDialogue("O longo túnel cinzento se abre, despejando o casal na imensidão monótona dos Campos de Asfódelo. A planície é um mar de poeira e flores pálidas onde milhares de almas vagam sem rumo, presas a um limbo de apatia onde a própria identidade se dissolve lentamente.");
        scene1.addNewDialogue("Aqui, o perigo não é o fogo ou o aço, mas o esquecimento. Conforme avançam pela névoa densa, Eurídice reduz o passo. Seu olhar perde o brilho, tornando-se translúcido e vago como o das outras sombras que os cercam.");
        scene1.addNewDialogue("Eurídice: Orfeu... Há quanto tempo estamos caminhando? O ar aqui é pesado... Sinto como se os meus pensamentos estivessem se desfazendo como fumaça.");
        scene1.addNewDialogue("Ela leva a mão translúcida à testa, franzindo o cenho em um esforço doloroso para focar a mente.");
        scene1.addNewDialogue("Eurídice: Diga-me... Qual era mesmo a cor da túnica que usei no dia do nosso casamento? Era branca, ou... ou azul como o mar? Eu não consigo lembrar. E o som da sua lira... parece apenas um zumbido distante na minha cabeça.");
        scene1.addNewDialogue("Como Orfeu reage ao pânico iminente de ver a esposa se apagando para sempre?");

        scene1.addNewChoice("Deixe-me cantar para você, reviver cada segundo do que fomos", "s1a_asphodel_mist", 0, 1, 1, 0, 0);
        scene1.addNewChoice("Ainda estamos juntos, não deixe que este lugar leve o que é nosso", "s1b_asphodel_mist", 0, 0, 0, 1, 0);
        scene1.addNewChoice("Concentre-se em andar e pare de prestar atenção a essas névoas tolas!", "s1c_asphodel_mist", 0, 0, 0, 0, 0);

        // Cenas 1a, 1b e 1c - Resultados das escolhas
        scene1a.addNewDialogue("Orfeu sente o desespero rasgar sua garganta, mas aproxima-se dela, segurando a harpa bem junto ao peito. Ele começa a entoar uma melodia extremamente suave, sussurrada quase como uma prece, costurando cada nota com memórias e nomes para ancorar a alma dela à realidade. Eurídice fecha os olhos, deixando as lágrimas reais lavarem o rosto enquanto o som resgata o calor de sua própria história.");

        scene1b.addNewDialogue("Orfeu engole o nó de choro que aperta seu peito, sentindo a exaustão mental e física cobrar seu preço de forma impiedosa. Ele toca uma balada melancólica e dolorosa sobre a perda e a lembrança, dividindo sua própria sanidade com ela para mantê-la sã. Ela aperta os braços, encontrando na dor compartilhada um escudo contra a névoa fria.");

        scene1c.addNewDialogue("Orfeu endurece a voz, já exausto e mentalmente esgotado pelo peso da jornada.");
        scene1c.addNewDialogue("Orfeu: Não há tempo para devaneios, Eurídice! Apenas continue seguindo o som dos meus passos e ignore o que este lugar tenta te roubar!");
        scene1c.addNewDialogue("Ela morde os lábios, sentindo-se profundamente sozinha e desamparada, enquanto a névoa cinzenta avança um pouco mais sobre sua mente.");

        // Cena 2 - Os Ecos da Distância
        scene2.addNewDialogue("A caminhada pela planície cinzenta consome o que resta das forças de ambos. Orfeu sente os braços dormentes de tanto sustentar a harpa e manter o foco absoluto em guiar o caminho, enquanto sua mente se fragmenta sob o peso do estresse e do terror de olhar para trás por engano.");
        scene2.addNewDialogue("Atrás dele, Eurídice oscila entre momentos de lucidez e lapsos profundos de apatia, murmurando nomes de pessoas e lugares que ela própria começa a confundir com o pó de Asfódelo.");
        scene2.addNewDialogue("Eurídice: Orfeu... Sinto que se eu fechar os olhos por um segundo sequer, eu vou esquecer o calor do sol. Por que tudo aqui parece tão mais fácil de aceitar do que a dor de viver?");
        scene2.addNewDialogue("Como Orfeu responde ao cansaço supremo e à desistência silenciosa de Eurídice?");

        scene2.addNewChoice("Resista mais um pouco, meu amor, o nosso recomeço está logo ali", "s2a_distant_echoes", 0, 2, 1, 0, 0);
        scene2.addNewChoice("A dor de carregar essa lembrança é o preço por estarmos vivos", "s2b_distant_echoes", 0, 1, 0, 0, 0);
        scene2.addNewChoice("Reclame menos e ande mais, eu estou carregando todo o peso desta jornada!", "s2c_distant_echoes", 0, -5, -2, -2, 0);

        // Cenas 2a, 2b e 2c - Resultados das escolhas
        scene2a.addNewDialogue("Orfeu inspira fundo, forçando as pernas trêmulas a continuarem andando, emanando uma esperança desesperada.");
        scene2a.addNewDialogue("Orfeu: Eu carregarei suas memórias por nós dois até sairmos daqui. Confie em mim.");
        scene2a.addNewDialogue("Eurídice assente fracamente, amparando-se na força inabalável que ele ainda tenta sustentar.");

        scene2b.addNewDialogue("Orfeu fala com um fio de voz, completamente esgotado física e mentalmente pela pressão do submundo.");
        scene2b.addNewDialogue("Orfeu: Lembrar dói, Eurídice, mas o esquecimento é a verdadeira morte. Vamos sofrer juntos, mas vamos sair daqui.");
        scene2b.addNewDialogue("Ela concorda em silêncio, aceitando o fardo pesado da existência.");

        scene2c.addNewDialogue("Orfeu estala com irritação, perdendo a paciência com a lentidão e a confusão dela.");
        scene2c.addNewDialogue("Orfeu: Você acha que é fácil para mim? Mantenha o foco e pare de fraquejar agora!");
        scene2c.addNewDialogue("Eurídice se enclausura em completo silêncio, engolindo a confusão mental e marchando como uma autômata fria e distante.");

        // Cena 3 - Às Margens do Estige
        scene3.addNewDialogue("A planície de Asfódelo finalmente termina, dando lugar a uma costa lamacenta e cinzenta. O cheiro de lodo e o ar denso anunciam o fim desta etapa da travessia.");
        scene3.addNewDialogue("Ao longe, cortando as águas escuras e paradas, ergue-se a silhueta familiar da margem oposta do rio Estige. Na penumbra do fundo, aguardando pacientemente junto à sua embarcação rústica, está o barqueiro Caronte, observando a aproximação exausta dos dois com seus olhos frios e indiferentes.");
        scene3.addNewDialogue("Orfeu e Eurídice param à beira da água. O bardo está com o corpo rígido, o suor frio escorrendo pela testa, e a mente no limite absoluto da exaustão após horas lutando contra a névoa do esquecimento. A penúltima barreira os aguarda.");

        chapter.newScene(scene1);
        chapter.newScene(scene1a);
        chapter.newScene(scene1b);
        chapter.newScene(scene1c);
        chapter.newScene(scene2);
        chapter.newScene(scene2a);
        chapter.newScene(scene2b);
        chapter.newScene(scene2c);
        chapter.newScene(scene3);

        return chapter;
    }

    private static Chapter BuildChapter9(){
        Chapter chapter = new Chapter("Capítulo 9 - O Rio Estíge");

        // Cena 1 - A Conversa com Caronte
        Scene scene1 = new Scene("s1_styx_intro", "s5_sisyphus_hostile");
        scene1.addNewDialogue("As águas densas e fétidas do rio Estige correm diante de Orfeu e Eurídice. Na margem, a barca de Caronte balança suavemente na névoa. O barqueiro os aguarda de braços cruzados sobre o remo, com o olhar cético e impaciente de quem não tem tempo a perder.");
        scene1.addNewDialogue("Caronte: Vocês chegaram até aqui com vida e com a permissão dos deuses. Mostrem os óbolos exatos para a travessia e entrem logo no barco, pois não ficarei esperando eternamente.");
        scene1.addNewDialogue("Orfeu tateia rapidamente a pequena bolsa de couro dada por Hades para conferir o pagamento. De repente, Sísifo emerge das sombras, aproximando-se do grupo.");
        scene1.setCondition(new Condition("s2_sisyphus_friendly", "sisyphus", 0, 2, 0, 0, 0));
        chapter.newScene(scene1);

        // RAMIFICACAO SISIFO AMIGO 
        // Cena 2 - A Sombra Amigável (Afinidade com Sísifo Alta)
        Scene scene2 = new Scene("s2_sisyphus_friendly", "s3b_charon_rigidity");
        scene2.addNewDialogue("Sísifo abre um sorriso caloroso e caminha na direção de Orfeu para uma despedida amigável.");
        scene2.addNewDialogue("Sísifo: Meu caro bardo! Que alegria vê-lo chegar até aqui. Deixe-me dar um abraço de despedida antes que você cruze para o mundo dos vivos.");
        scene2.addNewDialogue("Num movimento rápido e sutil durante o abraço, Sísifo enfia a mão na bolsa de Orfeu e furta o estatero dourado entregue por Hades, afastando-se logo em seguida com uma piscadela cúmplice. Quando Orfeu percebe o rombo, já é tarde demais.");
        scene2.setCondition(new Condition("s3a_charon_help", "charon", 0, 3, 0, 0, 0));
        chapter.newScene(scene2);

        // Cena 3A - O Auxílio do Barqueiro (Afinidade com Caronte Alta)
        Scene scene3a = new Scene("s3a_charon_help", "s9_climax_threshold");
        scene3a.addNewDialogue("Com duas moedas a menos devido ao furto de Sísifo, Orfeu apela para o barqueiro.");
        scene3a.addNewDialogue("Caronte cruza os braços, mas solta um suspiro pesado, lembrando-se das interações anteriores com o bardo.");
        scene3a.addNewDialogue("Caronte: Pelos deuses... Que dor de cabeça desnecessária. Sobe logo nessa barca antes que eu perdoe essa minha mania de ter paciência.");
        scene3a.addNewDialogue("Caronte cede à passagem, salvando o dia e permitindo que o casal avance.");
        chapter.newScene(scene3a);

        // Cena 3B - A Rigidez do Barqueiro (Afinidade com Caronte Baixa)
        Scene scene3b = new Scene("s3b_charon_rigidity", "s4b_failed_resignation");
        scene3b.addNewDialogue("Caronte observa o prejuízo de Orfeu com total indiferença, mantendo a postura irredutível.");
        scene3b.addNewDialogue("Caronte: Problema de seu descuido. Minha única responsabilidade é cobrar o óbolo exato. Como faltam duas moedas, não há travessia.");
        scene3b.addNewDialogue("Orfeu precisa lidar com a falta de pagamento e com a própria fúria perante a injustiça.");
        scene3b.setCondition(new Condition("s4a_melancholy_weight", null, 2, 0, 0, 5, 0));
        chapter.newScene(scene3b);

        // Cena 4A - O Peso da Melancolia (Tristeza Alta)
        Scene scene4a = new Scene("s4a_melancholy_weight", "s9_climax_threshold");
        scene4a.addNewDialogue("Tomado por uma dor profunda e um pranto devastador ao ver Eurídice imobilizada, o desespero emocional de Orfeu comove vagamente o ambiente, gerando uma abertura trágica, mas salvadora, que força uma reviravolta na margem e permite que prossigam.");
        chapter.newScene(scene4a);

        // Cena 4B - A Resignação Fracassada (Tristeza Baixa)
        Scene scene4b = new Scene("s4b_failed_resignation", "s8_ending4");
        scene4b.addNewDialogue("A falta de uma reação enérgica ou de uma dor expressada com força suficiente faz com que o bardo paralise. Sísifo leva a melhor, a oportunidade evapora e o rio permanece intransponível.");
        scene4b.addNewDialogue("[Final 4: Orfeu fracassa e Sísifo escapa]");
        chapter.newScene(scene4b);

        // RAMIFICACAO SISIFO INIMIGO
        // Cena 5 - A Emboscada Hostil (Afinidade com Sísifo Baixa)
        Scene scene5 = new Scene("s5_sisyphus_hostile", "s6b_charon_neutrality");
        scene5.addNewDialogue("Sísifo não se aproxima com carinho; ele salta de trás de uma rocha, avança diretamente sobre Eurídice e a agarra com força pelos braços, imobilizando-a como refém.");
        scene5.addNewDialogue("Sísifo: Nem ouse dar um passo em frente, Orfeu! Sua desconfiança e seu desrespeito lá no abismo me custaram caro. A sua esposa agora é minha garantia para forçar Caronte a me levar daqui no lugar de vocês!");
        scene5.setCondition(new Condition("s6a_charon_exception", "charon", 0, 3, 0, 0, 0));
        chapter.newScene(scene5);

        // Cena 6A - A Intervenção Excepcional (Afinidade com Caronte Alta)
        Scene scene6a = new Scene("s6a_charon_exception", "s9_climax_threshold");
        scene6a.addNewDialogue("Com Eurídice feita de refém por Sísifo, Orfeu implora por ajuda. Apesar da relutância, a afinidade prévia com Caronte pesa a favor do bardo.");
        scene6a.addNewDialogue("Caronte: Eu não sou polícia de almas, mas essa audácia no meu rio já passou dos limites.");
        scene6a.addNewDialogue("O barqueiro usa seu remo para afastar Sísifo e abrir brecha para resgatarem Eurídice e embarcar.");
        chapter.newScene(scene6a);
        
        // Cena 6B - A Neutralidade do Barqueiro (Afinidade com Caronte Baixa)
        Scene scene6b = new Scene("s6b_charon_neutrality");
        scene6b.addNewDialogue("Caronte vira o rosto, recusando-se a intervir no sequestro de Eurídice.");
        scene6b.addNewDialogue("Caronte: O problema entre vocês dois não me diz respeito. Resolvam-se na terra firme ou fiquem na margem.");
        scene6b.addNewDialogue("Orfeu se vê encurralado, precisando contar com o peso de sua própria dor e melancolia para tentar salvar a esposa.");
        chapter.newScene(scene6b);


        // Cena 7A - O Impulso da Fúria (Raiva Alta)
        Scene scene7a = new Scene("s7a_wrath_impulse", "s9_climax_threshold");
        scene7a.addNewDialogue("Consumido pela indignação com o furto e com a frieza do barqueiro, Orfeu desconta toda a sua frustração exigindo e ameaçando desafiar as regras do rio, impressionando Caronte com sua marra ou encontrando uma brecha na própria revolta para forçar a passagem.");
        chapter.newScene(scene7a);

        // Cena 7B - O Desespero Silencioso (Raiva Baixa)
        Scene scene7b = new Scene("s7b_silent_despair", null);
        scene7b.addNewDialogue("Sem a fúria necessária para impor sua vontade e sem as moedas completas, Orfeu apenas aceita a derrota em silêncio, vendo o barco partir sem eles.");
        scene7b.addNewDialogue("[Final 4: Orfeu fracassa e Sísifo escapa]");
        scene2.setCondition(new Condition("s9_climax_threshold", null, 3, 0, 0, 0, 5));
        chapter.newScene(scene7b);

        // Cena 8 - Final 4
        Scene scene8 = new Scene("s8_ending4", null);
        scene8.addNewDialogue("[Final 4: Orfeu fracassa e Sísifo escapa]");
        scene8.isEnding();
        chapter.newScene(scene8);

        // Cena 8 - O Limiar do Clímax
        Scene scene9 = new Scene("s9_climax_threshold", null);
        scene9.addNewDialogue("Superados os percalços na margem do Estige - seja contornando o furto de Sísifo ou contornando o sequestro com a ajuda de Caronte -, Orfeu e Eurídice finalmente garantem a travessia final. A barca corta as águas escuras rumo à última etapa da jornada, preparando o terreno para o julgamento definitivo.");
        chapter.newScene(scene9);


        return chapter;
    }

    private static Chapter BuildChapter10(){
        Chapter chapter = new Chapter("Capítulo 10 - Não olhe para trás");

        // Cena 1 - O Último Degrau
        Scene scene1 = new Scene("Último Degrau", "Última Conversa");
        scene1.addNewDialogue("A claridade fraca e prateada da superfície começa a se filtrar pelas fendas da rocha acima. O ar gélido e puro da Terra substitui o fedor de lodo do Submundo. Orfeu e Eurídice caminham pelo trecho final da subida, exaustos, pisando nas últimas pedras que separam a morte da vida.");
        scene1.addNewDialogue("A saída está logo ali na frente, a poucos metros de distância, banhada por uma luz real e natural. Mas o silêncio que se estende entre eles mudou de tom; tornou-se frágil, quase imperceptível.");
        scene1.addNewDialogue("Eurídice para de caminhar por um instante, respirando fundo o ar fresco com uma expressão de melancolia profunda.");
        scene1.addNewDialogue("Eurídice: Orfeu... Nós conseguimos. Estamos prestes a pisar no mundo dos vivos de novo. Mas... eu ainda carrego o eco deste lugar no meu peito. Você tem certeza de que há um lugar para mim lá em cima, ao seu lado?");
        chapter.newScene(scene1);
        
        // Cena 2 - A Última Conversa e o Silêncio
        Scene scene2 = new Scene("Última Conversa", "Olhar para trás");
        scene2.addNewDialogue("Orfeu continua de costas, obedecendo à rigorosa regra imposta por Hades, mas estende a mão para trás para tentar tocá-la ou sentir sua proximidade.");
        scene2.addNewDialogue("Orfeu: Não há dúvidas, meu amor. O sol vai nos aquecer, a nossa história não terminou nas sombras. Segure minha mão, estamos quase livres.");
        scene2.addNewDialogue("No entanto, conforme os ventos da superfície uivam na fenda da montanha, abafando os sons ao redor, algo aterrorizante acontece. Orfeu de repente percebe que o som dos passos de Eurídice sumiu por completo. Ele tenta tatear o ar com a mão estendida, mas não encontra nada além do vazio gélido.");
        scene2.addNewDialogue("O silêncio absoluto toma o lugar da presença dela. O pânico gela o sangue do bardo: Será que ela escorregou? Será que desistiu e ficou para trás? Ou será que os deuses a levaram de volta?");
        scene2.addNewDialogue("Como o destino de Orfeu e Eurídice se sela neste último momento?");
        scene2.setCondition(new Condition("Teste Afinidade", null, 1, 0, 10, 0, 0));
        chapter.newScene(scene2);

        // Cena 3 - teste amor
        Scene scene3 = new Scene("Teste Afinidade", "Olhar para trás");
        scene3.setCondition(new Condition("Não Olhar", "eurydice", 0, 10, 0, 0, 0));
        chapter.newScene(scene3);

        // Cena 4 - teste relacionamento
        Scene scene4 = new Scene("Olhar para trás", null);
        scene4.addNewChoice("Ceder ao pânico e olhar para trás", "Final 1: Olhe para trás", 0, 0, 0, 0, 99);
        chapter.newScene(scene4);

        // Cena 5 - [Final 1: Olhe para trás]
        Scene scene5 = new Scene ("Final 1: Olhe para trás", null);
        scene5.addNewDialogue("O desespero do silêncio e a incerteza corroem a pouca fé que restava em Orfeu.");
        scene5.addNewDialogue("Incapaz de suportar a ideia de que ela tenha desaparecido na penumbra sem uma resposta, o bardo cede ao pânico e comete o erro fatal:");
        scene5.addNewDialogue("ele vira o rosto e olha para trás para verificar se ela ainda está lá.");
        scene5.addNewDialogue("Resultado: Os olhos de Eurídice encontram os dele por um único segundo antes de ela começar a desvanecer em névoa cinzenta, puxada de volta para o abismo para sempre.");
        scene5.addNewDialogue("[Final 1: Olhe para trás]");
        scene5.isEnding();
        chapter.newScene(scene5);

        // Cena 6
        Scene scene6 = new Scene ("Não Olhar", null);
        scene6.addNewChoice("Resistir à tentação e confiar", "Final 2: Não olhe para trás", 0, 99, 99, 0, 0);
        chapter.newScene(scene6);

        // Cena 7 - [Final 2: Não olhe para trás]
        Scene scene7 = new Scene("Final 2: Não olhe para trás", null);
        scene7.addNewDialogue("Mesmo com o coração disparado e o silêncio ensurdecedor cortando sua mente,");
        scene7.addNewDialogue("Orfeu lembra-se de todo o laço que construíram, da força do amor e da confiança mútua que cultivaram a cada passo.");
        scene7.addNewDialogue("Apertando os punhos com uma convicção inabalável, ele resiste à tentação de virar o pescoço.");
        scene7.addNewDialogue("Orfeu: Não... Eu confio em você. Venha, Eurídice!");
        scene7.addNewDialogue("Um segundo depois, a respiração frágil dela ecoa logo atrás, e a mão gelada, porém firme, toca os dedos dele.");
        scene7.addNewDialogue("Dando o passo final juntos, eles atravessam a fenda e saem sob a luz aberta do sol da Grécia.");
        scene7.addNewDialogue("[Final 2: Não olhe para trás]");
        scene7.isEnding();
        chapter.newScene(scene7);

        return chapter;
    }

}