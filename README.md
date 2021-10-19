## Desafio Android

![Screenshot do app funcionando](image/screenshot.png)

Um dos desafios de qualquer desenvolvedor ou time de desenvolvimento é lidar com código legado, e no Digio isso não é 
diferente. Por conta disso, neste desafio vamos propor a refactoração de um código legado, visando a escalabilidade da 
aplicação e o desempenho.

Com o passar do tempo identificamos alguns problemas que impedem esse aplicativo de escalar e acarretam problemas de 
experiência do usuário. A partir disso elaboramos a seguinte lista de requisitos que devem ser cumpridos ao melhorar 
nossa arquitetura:

 - Em mudanças de configuração o aplicativo perde o estado da tela. Gostaríamos que o mesmo fosse mantido.
 - Nossos relatórios de crash têm mostrado alguns crashes relacionados a campos que não deveriam ser nulos sendo nulos 
   e gerenciamento de lifecycle. Gostaríamos que fossem corrigidos.
 - Gostaríamos de cachear os dados retornados pelo servidor.
 - Haverá mudanças na lógica de negócios e gostaríamos que a arquitetura reaja bem a isso.
 - Haverá mudanças na lógica de apresentação. Gostaríamos que a arquitetura reaja bem a isso.
 - Com um grande número de desenvolvedores e uma quantidade grande de mudanças ocorrendo testes automatizados são 
   essenciais.
   - Gostaríamos de ter testes unitários testando nossa lógica de apresentação, negócios e dados independentemente, 
     visto que tanto a escrita quanto execução dos mesmos são rápidas.
   - Por outro lado, testes unitários rodam em um ambiente de execução diferenciado e são menos fiéis ao dia-a-dia de 
     nossos usuários, então testes instrumentados também são importantes.

Boa sorte! =)