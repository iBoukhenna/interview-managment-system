<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${mailDataDto.subject}</title>
</head>
<body>
<div style="margin-top: 10px">Cher(e), ${mailDataDto.technicalAdvisorName}</div>
<div>Nous avons attribué cet entrevue à un autre referent technique, nous avons grandement apprécié votre disponibilité et votre volonté d'apporter votre expertise à ce processus de recrutement. Pour obtenir de plus amples détails, veuillez cliquer sur le lien suivant : <b>${mailDataDto.linkInterviewDetail}</b></div>
<div>Si, pour quelque raison que ce soit, nous avons besoin de votre participation à l'avenir, nous vous contacterons immédiatement. Votre expertise technique est précieuse et nous n'hésiterons pas à faire appel à vous si cela s'avère nécessaire.</div>
<br/>
<div>Cordialement.</div>
</body>
</html>
