<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${mailDataDto.subject}</title>
</head>
<body>
<div style="margin-top: 10px">Cher(e), ${mailDataDto.recruiterName}</div>
<div>Nous tennons à vous informer que nous avons reçu une réponse positive de la part d'un référent technique concernant l'entrevue. Pour obtenir de plus amples détails, veuillez cliquer sur le lien suivant : <b>${mailDataDto.linkInterviewDetail}</b></div>
<div>Pour obtenir de plus amples informations sur le référent technique qui mènera l'entrevue, veuillez consulter le profil de : <b>${mailDataDto.linkTechnicalAdvisorDetail}</b></div>
<br/>
<div>Cordialement.</div>
</body>
</html>

