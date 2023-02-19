
  const updateBtns = document.querySelectorAll('.js-update');
  const modal = document.querySelector('.js-modal');
  const modalClose = document.querySelector('.js-modal-close');
  const modalCancel = document.querySelector('.js-modal-cancel');
  const modalContainer = document.querySelector('.js-modal-container');



  const deleteBtns = document.querySelectorAll('.js-deleteAddress');
  const deletead = document.querySelector('.js-delete');
  const deleteClose = document.querySelector('.js-delete-close');
  const deleteCancel = document.querySelector('.js-delete-cancel');
  const deleteContainer = document.querySelector('.js-delete-container');

  function showUpdate(){
    modal.classList.add('open');
  }

  function hideUpdate(){
    modal.classList.remove('open');
  }

  function showDelete(){
    deletead.classList.add('open');
  }

  function hideDelete(){
    deletead.classList.remove('open');
  }

  // Update Address

  for(const updateBtn of updateBtns){
    updateBtn.addEventListener('click', showUpdate);
  }

  modalClose.addEventListener('click', hideUpdate);

  modalCancel.addEventListener('click', hideUpdate);

  modal.addEventListener('click', hideUpdate);

  modalContainer.addEventListener('click',function(event){
    event.stopPropagation();
  });

  // Delete Address
  for(const deleteBtn of deleteBtns){
    deleteBtn.addEventListener('click', showDelete);
  }

  deleteClose.addEventListener('click', hideDelete);

  deleteCancel.addEventListener('click', hideDelete);

  deletead.addEventListener('click', hideDelete);

  deleteContainer.addEventListener('click',function(event){
    event.stopPropagation();
  });

  
