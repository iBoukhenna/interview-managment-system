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
<div>Nous avons le plaisir de vous informer qu'une nouvelle demande d'entrevue technique a été soumise. Pour obtenir de plus amples détails, veuillez cliquer sur le lien suivant : <b>${mailDataDto.linkInterviewDetail}</b></div>
<div>Si vous souhaitez accepter cette demande, veuillez cliquer sur le lien ci-dessous : <b>${mailDataDto.linkAcceptInterview}</b></div>
<div>Dans le cas où vous ne souhaiteriez pas poursuivre avec cette demande, veuillez cliquer sur le lien suivant pour la refuser : <b>${mailDataDto.linkRejectInterview}</b></div>
<br/>
<div>Cordialement.</div>
</body>
</html>
