<!-- Modal -->
<div class="modal fade" id="qrcodemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalLabel">右键点击保存图片到本地</h4>
      </div>
      <div class="modal-body">
        <img src=""  id="qrcodeImg" class="img-rounded">
      </div>
      <div class="modal-footer">
        <button type="button" id="dismiss" class="btn btn-default" data-dismiss="modal">取消</button>
        <a id="download" hidden class="btn btn-primary">下载</a>
      </div>
    </div>
  </div>
</div>
<input type="hidden" id="wechatId" value="1">